

public class longestCommonPrefix {

  
  String LCP(String[] strings) 
  {
    int arr_len = strings.length;
    int q = 0;
    if (arr_len == 0) return "";

    String prefix = strings[0];
    char[] c_prefix = prefix.toCharArray();
    int prefix_len = prefix.length();
    for (int k=1 ; k<arr_len ; k++) {
      String current = strings[k];
      int str_len = current.length();    // Current string length
      q = prefix_len - str_len;
      char[] c_current = current.toCharArray();
      if (q < 0) q = 0;

      for (q++ ; q<str_len ;) {
        if (c_current[str_len-q-1] == c_prefix[str_len-q-1]) {
          prefix = String.valueOf(c_current[str_len-q-1]);
        }

      }
    }
    return prefix;
  }


}
