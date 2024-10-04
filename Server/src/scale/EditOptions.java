package scale;

import model.Automobile;

public class EditOptions implements Runnable {

    private Automobile a1;
    private String optionSetName;
    private String oldName;
    private String newName;

    public EditOptions(Automobile inputA1, String inputOptionSet, String inputOld, String inputNew) {

        this.a1 = inputA1;
        this.optionSetName = inputOptionSet;
        this.oldName = inputOld;
        this.newName = inputNew;

    }

    @Override
    public void run() {

        a1.updateOptName(optionSetName, oldName, newName);

    }
}
