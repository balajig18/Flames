package org.fun.flames.bgsource;

public class Flames {

	public static int verify(String name1, String name2)
			throws RuntimeException {

		int l1, l2, i, total, l;

		l1 = name1.length();
		l2 = name2.length();
		l = l1 + l2;
		for (i = 0; i < l1; ++i) {
			if (name1.contains(" "))
				--l1;
		}
		for (i = 0; i < l2; ++i) {
			if (name2.contains(" "))
				--l2;
		}
		total = l1 + l2;
		if (total != l) {
			throw new RuntimeException("enter valid names");
		}
		return total;
	}


	public int calculate(String name1, String name2) {

		// TODO Auto-generated method stub
		char ch, str[] = { 'f', 'l', 'a', 'm', 'e', 's' };
	     int rel=10;
		int i = 0, j, l1, l2, total = 0, index, t, index2 = -1, len = 6, main = 1, sub = 1, l = 0;
		l1 = name1.length();
		l2 = name2.length();
		try {
			total = verify(name1, name2);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		char n1[] = name1.toCharArray();
		char n2[] = name2.toCharArray();
		for (i = 0; i < l1; ++i) {
			ch = n1[i];
			for (j = 0; j < l2; ++j) {
				if (ch == n2[j]) {
					total = total - 2;
					n1[i] = ']';
					n2[j] = '[';
					break;
				}
			}
		}
		while (len > 1) {
			t = total;
			index = index2;
			while (t != 0) {
				if (main != 1 && sub == 1) {
					--t;
					if (index >= len)
						index = 0;
					++sub;
				} else {
					++index;
					--t;
					if (index >= len)
						index = 0;
				}
			}
			index2 = index;
			for (i = index; i < (len - 1); ++i)
				str[i] = str[i + 1];
			--len;
			main = 0;
			sub = 1;
		}
		if (str[0] == 'f')
			rel = 0;
		else if (str[0] == 'l')
			rel = 1;
		else if (str[0] == 'a')
			rel = 2;
		else if (str[0] == 'm')
			rel =3;
		else if (str[0] == 'e')
			rel = 4;
		else if (str[0] == 's')
			rel = 5;

		// System.out.print(rel);
		return rel;

	}

}
