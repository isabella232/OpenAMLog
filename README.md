OpenAMLog
=========

Read OpenAMLog with Intellij

OpenAMLog project is a Intellij plugin for helping the support OpenAM team to investigate on logs. Add the "amlog" extension to your OpenAM log, open it with Intellij and you will be able to use the plugin for analysing your log.
OpenAMLog plugin provide a language support for OpenAM log format. It means it will be able to parse and interpret this log file. You will be able to enjoy the text color and a dedicated tool window.



You can find more information about it in the wiki : https://github.com/qcastel/OpenAMLog/wiki


=========

Install the development environment

- Do a git clone of the project.
- Open it with intellij as a Intellij plugin
You have now to set up the dependencies :
- File > Project Structure...
You can follow this http://bjorn.tipling.com/how-to-make-an-intellij-idea-plugin-in-30-minutes it was useful for me
You can compile it with Java 7 but keep in mind that you will have to switch to Java 6 for doing the deployment.

=========

Install the plugin in intellij

The plugin in a jar that you should import in intellij.
Go to Preferences > Plugins
click on "install plugin from disk"

If you have any issue, the plugin will be auto-disable, so don't worry for that.