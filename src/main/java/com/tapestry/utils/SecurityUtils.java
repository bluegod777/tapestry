package com.tapestry.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils
{
	public static String hashPassword(final String password)
	{
		try
		{
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// String hashedPassword = passwordEncoder.encode("admin");
			String hashedPassword = passwordEncoder.encode(password);

			return hashedPassword;
		}

		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}
}
