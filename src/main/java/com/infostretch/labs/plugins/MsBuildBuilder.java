package com.infostretch.labs.plugins;

import com.infostretch.labs.transformers.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Handles transformation of MsBuildBuilder properties
 *
 */
public class MsBuildBuilder extends Plugins{

    public MsBuildBuilder(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformBuild() {
        appendBuildSteps("\t\t// MsBuildBuilder build step");

	String msBuildName = getElementByTag("msBuildName").getTextContent();
	String msBuildFile = getElementByTag("msBuildFile").getTextContent();
	String msBuildArgs = getElementByTag("cmdLineArgs").getTextContent() + " /nodeReuse:false";

	String msBuildCmd = "tool \'%s\' %s %s";

	msBuildCmd = String.format(msBuildCmd, msBuildName, msBuildArgs, msBuildFile);
	    
	appendBuildSteps("\nbat \"\"\" \n" + msBuildCmd + " \n \"\"\"");
    }
}
