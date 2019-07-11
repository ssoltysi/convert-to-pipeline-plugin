package com.infostretch.labs.plugins;

import com.infostretch.labs.transformers.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Handles transformation of PowerShell properties
 *
 */
public class PowerShell extends Plugins{

    public PowerShell(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformBuild() {
        appendBuildSteps("\t\t// PowerShell build step");
        String command  = getElementByTag("command").getTextContent();
	appendBuildSteps("\npowershell \"\"\" \n" + command + " \n \"\"\"");
    }
}
