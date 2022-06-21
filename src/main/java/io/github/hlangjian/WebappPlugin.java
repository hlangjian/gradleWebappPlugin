package io.github.hlangjian;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskContainer;

public class WebappPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    WebappPluginExtension extension = project
      .getExtensions()
      .create("webapp", WebappPluginExtension.class);
    TaskContainer tasks = project.getTasks();
    
    tasks.register(
      "buildWebapp",
      BuildFrontendTask.class,
      task -> {
        task.getAppDir().set(extension.getAppDir());
        task.getBuildCommand().set(extension.getBuildCommand());
      }
    );
    tasks.register(
      "bundleWebapp",
      BundleFrontendTask.class,
      task -> {
        task.getFrom().set(extension.getFrom());
        task.getInto().set(extension.getInto());
        task.getIncludePatterns().set(extension.getInclude());
      }
    );
    tasks
      .getByName("bundleWebapp")
      .dependsOn("buildWebapp")
      .dependsOn("processResources");
    tasks.getByName("jar").dependsOn("bundleWebapp");
    tasks.getByName("test").dependsOn("bundleWebapp");
    tasks.getByName("compileTestJava").dependsOn("bundleWebapp");
  }
}
