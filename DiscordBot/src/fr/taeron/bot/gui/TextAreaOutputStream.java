/*
 * Copyright 2017 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.taeron.bot.gui;

import java.awt.EventQueue;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;

public class TextAreaOutputStream extends OutputStream {

	private byte[] oneByte;
	private Appender appender;

	public TextAreaOutputStream(JTextArea txtara) {
		this(txtara, 1000);
	}

	public TextAreaOutputStream(JTextArea txtara, int maxlin) {
		if (maxlin < 1) {
			throw new IllegalArgumentException(
					"TextAreaOutputStream maximum lines must be positive (value=" + maxlin + ")");
		}
		oneByte = new byte[1];
		appender = new Appender(txtara, maxlin);
	}

	public synchronized void clear() {
		if (appender != null) {
			appender.clear();
		}
	}

	@Override
	public synchronized void close() {
		appender = null;
	}

	@Override
	public synchronized void flush() {
	}

	@Override
	public synchronized void write(int val) {
		oneByte[0] = (byte) val;
		write(oneByte, 0, 1);
	}

	@Override
	public synchronized void write(byte[] ba) {
		write(ba, 0, ba.length);
	}

	@Override
	public synchronized void write(byte[] ba, int str, int len) {
		if (appender != null) {
			appender.append(bytesToString(ba, str, len));
		}
	}

	static private String bytesToString(byte[] ba, int str, int len) {
		try {
			return new String(ba, str, len, "UTF-8");
		} catch (UnsupportedEncodingException thr) {
			return new String(ba, str, len);
		} 
	}


	static class Appender implements Runnable {
		private final JTextArea textArea;
		private final int maxLines; 
		private final LinkedList<Integer> lengths; 
		private final List<String> values; 

		private int curLength; 
		private boolean clear;
		private boolean queue;

		Appender(JTextArea txtara, int maxlin) {
			textArea = txtara;
			maxLines = maxlin;
			lengths = new LinkedList<>();
			values = new ArrayList<>();

			curLength = 0;
			clear = false;
			queue = true;
		}

		synchronized void append(String val) {
			values.add(val);
			if (queue) {
				queue = false;
				EventQueue.invokeLater(this);
			}
		}

		synchronized void clear() {
			clear = true;
			curLength = 0;
			lengths.clear();
			values.clear();
			if (queue) {
				queue = false;
				EventQueue.invokeLater(this);
			}
		}

		@Override
		public synchronized void run() {
			if (clear) {
				textArea.setText("");
			}
			values.stream().map((val) -> {
				curLength += val.length();
				return val;
			}).map((val) -> {
				if (val.endsWith(EOL1) || val.endsWith(EOL2)) {
					if (lengths.size() >= maxLines) {
						textArea.replaceRange("", 0, lengths.removeFirst());
					}
					lengths.addLast(curLength);
					curLength = 0;
				}
				return val;
			}).forEach((val) -> {
				textArea.append(val);
			});
			values.clear();
			clear = false;
			queue = true;
		}

		static private final String EOL1 = "\n";
		static private final String EOL2 = System.getProperty("line.separator", EOL1);
	}

} 