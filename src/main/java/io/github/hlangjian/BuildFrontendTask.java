package io.github.hlangjian;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import org.gradle.internal.os.OperatingSystem;

public abstract class BuildFrontendTask extends DefaultTask {

  @Input
  @Optional
  public abstract Property<String> getAppDir();

  @Input
  @Optional
  public abstract Property<String> getBuildCommand();

  @TaskAction
  public void action() {
    getProject().exec(task -> {
      boolean isWindows = OperatingSystem.current().isWindows();
      String interpreter = isWindows? "cmd.exe" : "bash";
      String arg = isWindows? "/c" : "-c";
      task.commandLine(interpreter, arg, getBuildCommand().getOrElse("npm install && npm run build"));
      task.workingDir(getAppDir().getOrElse("src/main/webapp"));
    });
  }
}
