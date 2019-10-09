# effective java 3rd edition

# 高效java第三版



## chapter2

## 第二章



**T**HIS chapter concerns creating and destroying objects: when and how to create them, when and how to avoid creating them, how to ensure they are destroyed in a timely manner, and how to manage any cleanup actions that must precede their destruction.

本章关注与创造和销毁对象：什么时候以及如何创造它们，何时及怎么避免创造它们，如何确保它们被一种及时的方式销毁，以及如何在销毁之前完成所有清理动作

### Item 1: Consider static factory methods instead of constructors

### 第一项：考虑静态工厂方法代替构造方法

The traditional way for a class to allow a client to obtain an instance is to provide a public constructor. There is another technique that should be a part of every programmer’s toolkit. A class can provide a public *static factory method*, which is simply a static method that returns an instance of the class. Here’s a simple example from Boolean (the *boxed primitive* class for boolean). This method translates a boolean primitive value into a Boolean object reference:

传统的方法是让一个类允许客户端获得实例而提供一个公共的构造器。还有一个方式：应该成为所有程序员工具包的一部分。一个类可以提供一个公共静态工厂方法，这个方法是一个简单的静态方法，返回一个实例类。这边有一个简单的Boolean类型的例子（基本类boolean的包装类）。这个方法将一个基本boolean值转变成一个Boolean对象引用：

```java
 public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
}
```



Note that a static factory method is not the same as the *Factory Method* pattern from *Design Patterns* [Gamma95]. The static factory method described in this item has no direct equivalent in *Design Patterns*.

注意，静态工厂方法与设计模式中的工厂方法不同，静态工厂方法在此的描述并不直接等同于设计模式

A class can provide its clients with static factory methods instead of, or in addition to, public constructors. Providing a static factory method instead of a public constructor has both advantages and disadvantages.

一个类可以提供给客户端静态工厂方法去替代或者添加公共的构造器。提供一个静态的工厂方法代替公共构造器有利有弊。

**One advantage of static factory methods is that, unlike constructors, they have names.** If the parameters to a constructor do not, in and of themselves, describe the object being returned, a static factory with a well-chosen name is easier to use and the resulting client code easier to read. For example, the constructor BigInteger(int, int, Random), which returns a BigInteger that is probably prime, would have been better expressed as a static factory method named BigInteger.probablePrime. (This method was added in Java 4.)

静态工厂方法的优点是有名字而不想构造器那样。如果构造器的参数本身没有描述返回的对象，那么一个有精心选择名字的静态工厂更易于使用，并且更易于生成的客户端代码的阅读。比如说。返回一个BigInteger可能是素数的构造器 BigInteger(int,int,Random)，而静态工厂方法的表述可能更佳妥当BigInteger.probablePrime（此方法是java4中添加的）

A class can have only a single constructor with a given signature. Programmers have been known to get around this restriction by providing two constructors whose parameter lists differ only in the order of their parameter types. This is a really bad idea. The user of such an API will never be able to remember which constructor is which and will end up calling the wrong one by mistake. People reading code that uses these constructors will not know what the code does without referring to the class documentation.

一个类只能有一个给定签名的构造器。程序员解决这个限制通过提供两个构造器，只要参数列表不同于先前的参数类型顺序即可。这是一个非常糟糕的想法。比如API的使用者从不会记得哪个构造器是哪个，并且最终错误地调用错误的构造器。别人阅读这段代码使用这些构造器并不知道这是做什么用的，在没有提供这个类的文档的时候。

Because they have names, static factory methods don’t share the restriction discussed in the previous paragraph. In cases where a class seems to require multiple constructors with the same signature, replace the constructors with static factory methods and carefully chosen names to highlight their differences.

因为它们有名字，静态工厂方法不受到先前段落所讨论到的限制。在类中可能需要用到多种单一签名的构造器情况下，用静态工厂方法代替构造器且细心挑选名字去突出它们到不同。

**A second advantage of static factory methods is that, unlike constructors, they are not required to create a new object each time they’re invoked.** This allows immutable classes (Item 17) to use preconstructed instances, or to cache instances as they’re constructed, and dispense them repeatedly to avoid creating unnecessary duplicate objects. The Boolean.valueOf(boolean) method illustrates this technique: it *never* creates an object. This technique is similar to the *Flyweight* pattern [Gamma95]. It can greatly improve performance if equivalent objects are requested often, especially if they are expensive to create.

静态工厂方法的第二个优点是，不像构造器那样，它们每次被调用的时候不需要创建一个新的对象。这个不允许改变的类使用提前构造实例，或者在实例时缓存，作为构造器，且重复分配它们时没有创造不需要的重复对象。Boolean.valueOf(boolean)这个方法说明此种方式：它从不创造对象，这个方式时与Flyweight 模式相似。它可以极大地提高性能，在经常请求相等对象时，特别是当它们创建是开销很昂贵的情况下

The ability of static factory methods to return the same object from repeated invocations allows classes to maintain strict control over what instances exist at any time. Classes that do this are said to be *instance-controlled.* There are several reasons to write instance-controlled classes. Instance control allows a class to guarantee that it is a singleton (Item 3) or noninstantiable (Item 4). Also, it allows an immutable value class (Item 17) to make the guarantee that no two equal instances exist: a.equals(b) if and only if a == b. This is the basis of the *Flyweight* pattern [Gamma95]. Enum types (Item 34) provide this guarantee.

静态工厂方法被重复调用时返回同一个对象的能力，在实例存在时的任何时候允许类维持严格的控制。这样做的类被称作实例控制。写实例控制类的原因有很多。实例控制允许一个类保证它是单例或者不可实例。同时，它允许一个不可改变的值类保证没有两个相等的实例存在。： a.equals(b) 当且仅当 a==b，这就是基本的 Flyweight 模式 枚举类型 提供这个保证。

**A third advantage of static factory methods is that, unlike constructors, they can return an object of any subtype of their return type.** This gives you great flexibility in choosing the class of the returned object.

静态工厂方法的第三个优点是，不像构造器那样，它们可以返回一个返回类型的任意子类型的对象。者给你一个极大的灵活性在选取类的返回对象时。

One application of this flexibility is that an API can return objects without making their classes public. Hiding implementation classes in this fashion leads to a very compact API. This technique lends itself to *interface-based frameworks*(Item 20), where interfaces provide natural return types for static factory methods

一个灵活的应用就在于API可以在返回对象时没有将它们的类公开的。隐藏实例类用这种方式可以使API非常紧凑。这种方式借出自己适用于基于接口的框架。当接口提供自然返回类型给静态工厂方法。

Prior to Java 8, interfaces couldn’t have static methods. By convention, static factory methods for an interface named Type were put in a *noninstantiable companion class* (Item 4) named Types*.* For example, the Java Collections Framework has forty-five utility implementations of its interfaces, providing unmodifiable collections, synchronized collections, and the like. Nearly all of these implementations are exported via static factory methods in one noninstantiable class (java.util.Collections). The classes of the returned objects are all nonpublic.

java8之前，接口不能有静态方法。按照规定，一个接口里名为Type的静态工厂方法被放在一个非实例化的伙伴类（名为Type）中。比如java的集合框架有45个工具实现其借口，提供不可修改的集合，同步集合，以及几乎所有实现出口都经由静态工厂方法，在一个非实例的类中（java.util.Collections）.这些类返回的对象都是不公开的

The Collections Framework API is much smaller than it would have been had it exported forty-five separate public classes, one for each convenience implementation. It is not just the *bulk* of the API that is reduced but the *conceptual weight:*the number and difficulty of the concepts that programmers must master in order to use the API. The programmer knows that the returned object has precisely the API specified by its interface, so there is no need to read additional class documentation for the implementation class. Furthermore, using such a static factory method requires the client to refer to the returned object by interface rather than implementation class, which is generally good practice (Item 64).

集合框架API小很多了相比于它输出45个单独的公开类，每个类有便利的实现。它不只是大量的API减少，同时也减少了概念的权重：程序员必须掌握大量且困难的概念才能使用API。程序员知道这个返回对象恰好在API接口上有详细的说明。所以不需要为这个实现类读多余的文档。而且，使用静态工厂方法需要客户端通过接口而不是通过实现类接收返回值对象，这通常是一个好的实践











