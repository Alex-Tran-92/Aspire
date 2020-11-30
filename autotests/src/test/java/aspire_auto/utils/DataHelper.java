package aspire_auto.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHelper {

    public static String getRegExrMatchingGroup(String inputString, String pattern, int matchingGroup) {
        String result = null;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputString);

        if (m.find()) {
            result = m.group(matchingGroup);
        }
        return result;
    }

    public static String[] splitStringToArrayByDelimiter(String inputStr, String delimiter) {
        String[] listString = inputStr.split(delimiter);
        for (int i = 0; i < listString.length; i++)
            listString[i] = listString[i].trim();
        return listString;
    }

    public static String[] convertStringListToArray(String inputStr) {
        return splitStringToArrayByDelimiter(inputStr, ",");
    }

    public static float convertStringToFloat(String floatString) {
        return Float.parseFloat(floatString);
    }

    public static int convertStringToInt(String intString) {
        return Integer.parseInt(intString);
    }

    /**
     * Round to certain number of decimals
     *
     * @param d
     * @param decimalPlace
     * @return
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * Note that this method will change permanently the order of input List
     */
    public static boolean isEqualIgnoringOrder(List<String> list1, List<String> list2) {
        Collections.sort(list1);
        Collections.sort(list2);
        return list1.equals(list2);
    }

    public static By convertToBy(String locatorType, String locatorValue) {
        By result = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                result = By.id(locatorValue);
                break;
            case "name":
                result = By.name(locatorValue);
                break;
            case "classname":
            case "class":
                result = By.className(locatorValue);
                break;
            case "tagname":
            case "tag":
                result = By.tagName(locatorValue);
                break;
            case "linktext":
            case "link":
                result = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                result = By.partialLinkText(locatorValue);
                break;
            case "cssselector":
            case "css":
                result = By.cssSelector(locatorValue);
                break;
            case "xpath":
                result = By.xpath(locatorValue);
                break;
        }
        return result;
    }

    /**
     * Get Json Object from Json File
     *
     * @param jsonPath
     * @param classOfT
     * @param objectType
     * @param objectName
     * @return
     */
    public static <T> T getObjectFromJsonFile(String jsonPath, Class<T> classOfT, String objectType, String objectName) throws IOException {
        InputStream inputStream = new FileInputStream(jsonPath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        JsonHelper json = new JsonHelper(inputStreamReader);

        T object = null;

        for (JsonHelper jsonElement : json.get(objectType)) {
            if (jsonElement.get("name").stringValue().equalsIgnoreCase(objectName)) {
                object = json.convertToJavaObject(jsonElement, classOfT);
                break;
            }
        }
        return object;
    }

    public static List<String> getUniqueListFromList(List<String> stringList) {
        return Arrays.asList(new LinkedHashSet<>(stringList).toArray(new String[]{}));
    }

    public static JsonHelper getJsonElement(String jsonFile, String... keys) {
        JsonHelper jsonElement = null;
        try {
            InputStream inputStream = new FileInputStream(jsonFile);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            jsonElement = new JsonHelper(inputStreamReader);
            for (String key : keys) {
                jsonElement = jsonElement.get(key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonElement;
    }

    public static String getPriceFromString(String priceString) {
        return priceString.replaceAll("[^0-9.,]", "");
    }

    public static String convertSymbolToCurrency(String price) {
        String currency = "";
        if (price.contains("$")) {
            currency = "USD";
        } else if (price.contains("€")) {
            currency = "Euro";
        }
        return currency;
    }

    public static String removeCharactersFromString(String inputStr, String... removedChars) {
        for (String removedChar : removedChars) {
            inputStr = inputStr.replace(removedChar, "");
        }
        return inputStr;
    }

    public static List<String> convertJsonArrayToList(JsonHelper jsonArray) {
        return Arrays.asList(new Gson().fromJson(jsonArray.jsonArray(), String[].class));
    }

    public static Map<String, String> convertJsonToMap(JsonHelper jsonObject) {
        return new Gson().fromJson(
                jsonObject.jsonObject(), new TypeToken<HashMap<String, String>>() {
                }.getType());
    }

    /*
     * Using substring to get text from inputStr
     * from: location of first character from string which want to get
     * char: last character from string which want to get
     * */
    public static String getTextFromString(String inputStr, int from, char character) {
        return inputStr.substring(from, inputStr.indexOf(character));
    }
}