package com.poc.security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Security credentials generator.
 * Usage:
 * java -cp [jar name][jar version] com.poc.security.SecurityCredentialsGen <STRATEGY> [-f] <USERNAME> <PASSWORD>
 *     -f - write an output into a file (otherwise will be written to console)
 *
 * @author Andrey Shchagin on 11/23/15.
 */
public class SecurityCredentialsGen {
	private static final Logger LOG = Logger.getLogger(AesToolkit.class.getName());

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println(
					"Usage: "+SecurityCredentialsGen.class.getCanonicalName()+" <STRATEGY> [-f] <USERNAME> <PASSWORD>");
			System.exit(0);
		}

		ASecurityToolkit sec = null;
		try {
			switch (EStrategy.valueOf(args[0])) {
				case AES :
					sec = new AesToolkit();
					break;
			}
		} catch (IllegalArgumentException err) {
			throw new RuntimeException(String.format("Unsupported strategy: %s", args[0]));
		}

		OutputStream[] outputStream = new OutputStream[2];
		int argumentIterationIndex = 1;
		try {
			if (args[1].equals("-f")) {
				++argumentIterationIndex;
				outputStream[0] = new FileOutputStream("uname.aes");
				outputStream[1] = new FileOutputStream("password.aes");
			} else {
				outputStream[0] = System.out;
				outputStream[1] = System.out;
			}
		} catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "Error during opening a file stream", e);
		}

		try {
			for (int i = argumentIterationIndex; i < args.length; i++)
				outputStream[i - argumentIterationIndex].write(sec.encrypt(args[i]).getBytes());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error during writing into a file stream", e);
		}

		try {
			for (OutputStream aStream : outputStream) {
				aStream.flush();
				aStream.close();
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error during closing a file stream", e);
		}
	}

	private enum EStrategy {
		AES, DES;
	}
}
