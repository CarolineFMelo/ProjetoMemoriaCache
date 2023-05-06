package functions;

public class Conversion {
	
	public static int[] intToBinary(int value, int size) {
        if (value > Math.pow(2, size) - 1) {
            return null;
        }
        int bin[] = new int[size];
        int i = 0;
        while (value > 0 && i < size) {
            int num = value % 2;
            value = value / 2;
            bin[i] = num;
            i++;
        }
        for (int j = 0; j <= size / 2; j++) {
            int temp = bin[j];
            bin[j] = bin[size - j - 1];
            bin[size - j - 1] = temp;
        }
        return bin;
    }

    public static String intToBinaryString(Long value, Long adressSize) {
        if (value > Math.pow(2, adressSize) - 1) {
            return null;
        }
        char bin[] = new char[adressSize.intValue()];
        for (int i = 0; i < adressSize; i++) {
            bin[i] = '0';
        }
        int i = 0;
        while (value > 0 && i < adressSize) {
            int num = value.intValue() % 2;
            value = value / 2;
            bin[i] = (num + "").charAt(0);
            i++;
        }
        for (int j = 0; j <= adressSize / 2; j++) {
            char temp = bin[j];
            bin[j] = bin[adressSize.intValue() - j - 1];
            bin[adressSize.intValue() - j - 1] = temp;
        }
        String nova = new String(bin);
        return nova;
    }
	
}
