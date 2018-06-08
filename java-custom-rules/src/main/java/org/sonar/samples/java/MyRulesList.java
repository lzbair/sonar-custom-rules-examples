package org.sonar.samples.java;

import com.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.samples.java.checks.*;

import java.util.List;

public final class MyRulesList {

  private MyRulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .add(AvoidMutableMemberRule.class)
      .build();
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
