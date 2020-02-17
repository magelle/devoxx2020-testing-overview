package properties;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class BasketItems extends Generator<BasketItem> {

    public static final List<String> ALL_FRUITS = List.of(CashRegisterPropertyTest.CERISES, CashRegisterPropertyTest.POMMES, CashRegisterPropertyTest.BANANES, CashRegisterPropertyTest.APPLES, CashRegisterPropertyTest.MELE);

    public BasketItems() {
        super(BasketItem.class);
    }

    @Override public BasketItem generate(
            SourceOfRandomness r,
            GenerationStatus status) {
        String fruit = r.choose(ALL_FRUITS);
        return new BasketItem(fruit, r.nextInt(1, 4));
    }
}


/*
java.lang.AssertionError: Property named 'notEmptyBasketShouldHaveAPositiveTotal' failed (
Expecting:
 <-25L>
to be greater than:
 <0L> )
With arguments: [[BasketItem{fruit='Cerises', number=1}, BasketItem{fruit='Mele', number=4}]]
Seeds for reproduction: [-7585557412995634682]

	at com.pholser.junit.quickcheck.runner.PropertyFalsified.counterexampleFound(PropertyFalsified.java:52)
	at com.pholser.junit.quickcheck.runner.ShrinkNode.fail(ShrinkNode.java:115)
	at com.pholser.junit.quickcheck.runner.Shrinker.shrink(Shrinker.java:90)
	at com.pholser.junit.quickcheck.runner.PropertyStatement.shrink(PropertyStatement.java:169)
	at com.pholser.junit.quickcheck.runner.PropertyStatement.lambda$property$3(PropertyStatement.java:146)
	at com.pholser.junit.quickcheck.runner.PropertyVerifier$1.evaluate(PropertyVerifier.java:88)
	at com.pholser.junit.quickcheck.runner.PropertyVerifier.verify(PropertyVerifier.java:69)
	at com.pholser.junit.quickcheck.runner.PropertyStatement.evaluate(PropertyStatement.java:106)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
Caused by: java.lang.AssertionError:
Expecting:
 <-25L>
to be greater than:
 <0L>
	at properties.CashRegisterPropertyTest.notEmptyBasketShouldHaveAPositiveTotal(CashRegisterPropertyTest.java:58)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at com.pholser.junit.quickcheck.runner.PropertyVerifier$2.evaluate(PropertyVerifier.java:106)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at com.pholser.junit.quickcheck.runner.PropertyVerifier$1.evaluate(PropertyVerifier.java:77)
	... 16 more



 */