package adapter;

public interface Choices {
    
    public String getOptionChoice(String modelName, String setName);

    public float getOptionChoicePrice(String modelName, String setName);

    public void setOptionChoice(String modelName, String setName, String optionName);

}
