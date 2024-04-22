import ReverseModule.ReversePOA; 

import java.lang.String; 

class ReverseImpl extends ReversePOA {
    ReverseImpl() {
        super();
        System.out.println("Reverse Object Created");
    }

    public String reverse_string(String name) {
        // Reverse the string
        StringBuffer str = new StringBuffer(name);
        str.reverse(); 
        // Return the reversed string
        return (("Server Send " + str));
    }

    public String concat_strings(String str1, String str2) {
        // Concatenate the two strings
        String result = str1 + str2;
        // Return the concatenated string
        return (("Server Send " + result));
    }
}
