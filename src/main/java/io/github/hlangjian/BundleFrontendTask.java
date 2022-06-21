package io.github.hlangjian;

import java.util.List;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

public abstract class BundleFrontendTask extends DefaultTask {

  @Input
  @Optional
  public abstract Property<String> getFrom();

  @Input
  @Optional
  public abstract Property<String> getInto();

  @Input
  @Optional
  public abstract ListProperty<String> getIncludePatterns();

  @TaskAction
  public void action() {
    getProject().copy(task -> {
      task.from(getFrom().getOrElse("src/main/webapp/dist"));
      task.into(getInto().getOrElse("build/resources/main/META-INF/resources"));
      task.include(getIncludePatterns().getOrElse(List.of("assets/*", "index.html")));
    });
  }
}
