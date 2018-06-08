package org.sonar.samples.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(
        key = "AvoidMutableMember",
        name = "Mutable object members are not allowed",
        description = "Object members should be declared final.",
        priority = Priority.CRITICAL,
        tags = {"safety"})
public class AvoidMutableMemberRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitClass(ClassTree tree) {
        super.visitClass(tree);
        tree.members().stream()
                .filter(m -> m.is(Tree.Kind.VARIABLE))
                .map(m -> ((VariableTree) m))
                .filter(v -> !v.symbol().isFinal())
                .forEach(v -> context.reportIssue(this, v.simpleName(), "Mark this member as final."));
    }
}
