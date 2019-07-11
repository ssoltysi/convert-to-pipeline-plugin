package com.infostretch.labs.plugins;

import com.infostretch.labs.transformers.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SlackNotifier extends Plugins {

    public SlackNotifier(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformPublisher() {
        appendBuildSteps("\n\t\t// SlackNotifier publisher step");
	
        String botUser = getElementByTag("botUser").getTextContent();
	String room = getElementByTag("room").getTextContent();
	String teamDomain = getElementByTag("teamDomain").getTextContent();
	String authToken = getElementByTag("authToken").getTextContent();
	String tokenCredentialId = getElementByTag("tokenCredentialId").getTextContent();

	String goodSlack = "slackSend botUser: %s, channel: \'%s\', color: \'good\', message: \'Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was successful\', teamDomain: \'%s\', token: \'%s\', tokenCredentialId: \'%s\'";
	goodSlack = String.format(goodSlack, botUser, room, teamDomain, authToken, tokenCredentialId);

	String badSlack = "slackSend botUser: %s, channel: \'%s\', color: \'danger\', message: \'Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was NOT successful\', teamDomain: \'%s\', token: \'%s\', tokenCredentialId: \'%s\'";
	badSlack = String.format(badSlack, botUser, room, teamDomain, authToken, tokenCredentialId);
	
	String status = "def status = currentBuild.currentResult";
	appendNotificationSteps("\n\t\t" + status + "\n");
	String slackLogic = "if (status == \'SUCCESS\') {\n %s \n} else {\n %s \n}";
	slackLogic = String.format(slackLogic, goodSlack, badSlack);
	appendNotificationSteps(slackLogic);
    }
}
