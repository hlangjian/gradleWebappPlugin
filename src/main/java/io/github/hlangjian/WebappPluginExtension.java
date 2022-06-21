package io.github.hlangjian;

import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;

public abstract class WebappPluginExtension {

  public abstract Property<String> getAppDir();

  public abstract Property<String> getBuildCommand();

  public abstract Property<String> getFrom();

  public abstract Property<String> getInto();

  public abstract ListProperty<String> getInclude();
}
