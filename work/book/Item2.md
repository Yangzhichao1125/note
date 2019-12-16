Item 2: Consider a builder when faced with many constructor parameters**

项目2:当一个构造器有很多参数时考虑使用builder模式

Static factories and constructors share a limitation: they do not scale well to large numbers of optional parameters. Consider the case of a class representing the Nutrition Facts label that appears on packaged foods. These labels have a few required fields—serving size, servings per container, and calories per serving— and more than twenty optional fields—total fat, saturated fat, trans fat, cholesterol, sodium, and so on. Most products have nonzero values for only a few of these optional fields.

静态工厂和构造器都有一个限制：它们无法很好的扩展到很多可选参数。考虑到类相当于一个袋包装食品上的营养物质标签这个情况，这些标签需要一些属性---分量多少，每次摄入量，以及每次摄入多少卡路里 ---当多于20哥选择属性---总的脂肪量，饱和脂肪，反式脂肪，胆固醇，纳，等等，大多产品都有非零值，只有少数几个可选属性

What sort of constructors or static factories should you write for such a class? Traditionally, programmers have used the *telescoping constructor* pattern, in which you provide a constructor with only the required parameters, another with a single optional parameter, a third with two optional parameters, and so on, culminating in a constructor with all the optional parameters. Here’s how it looks in practice. For brevity’s sake, only four optional fields are shown:

当你编写这样一个类时，应该如何选择构造器或静态工厂呢？传统上，程序员使用可伸缩构造器模式，该模式中你提供一个所需参数的构造器，另一个提供单一参数，三选其二参数，等等，最终一个完成一个带有所有参数的构造器。以下是它实践后的样纸。为了简洁起见，现只提供4个可选属性如下所示：

```java
public class NutritionFacts {
	private final int servingSize;   
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;  
	
	public NutritionFacts(int servingSize, int servings) {
	    this(servingSize, servings, 0);
	}
	
	public NutritionFacts(int servingSize, int servings,
	        int calories) {
	    this(servingSize, servings, calories, 0);
	}
	public NutritionFacts(int servingSize, int servings,
	        int calories, int fat) {
	    this(servingSize, servings, calories, fat, 0);
	}
	public NutritionFacts(int servingSize, int servings,
	        int calories, int fat, int sodium) {
	    this(servingSize, servings, calories, fat, sodium, 0);
	} 
	
	public NutritionFacts(int servingSize, int servings,
			int calories, int fat, int sodium, int carbohydrate) {
			this.servingSize = servingSize;     
			this.servings = servings;
			this.calories = calories;
			this.fat = fat;
			this.sodium = sodium;
			this.carbohydrate = carbohydrate;
	}
}

```



When you want to create an instance, you use the constructor with the shortest parameter list containing all the parameters you want to set:

当比想创建一个实例是，你可以使用包含你想要的最短参数的构造器:

NutritionFacts cocaCola =
new NutritionFacts(240, 8, 100, 0, 35, 27);

Typically this constructor invocation will require many parameters that you don’t want to set, but you’re forced to pass a value for them anyway. In this case, we passed a value of 0 for fat. With “only” six parameters this may not seem so bad, but it quickly gets out of hand as the number of parameters increases.

通常这个构造器被调用时需要许多你不想设置的参数，但你不管怎样都得通过舍定一个值才可以。在这种情况下，我们将脂肪含量低值设置为0.只有6个参数这看起来并不是很坏，但当参数增加时这很快就失控了。

In short, **the telescoping constructor pattern works, but it is hard to write client code when there are many parameters, and harder still to read it.** The reader is left wondering what all those values mean and must carefully count parameters to find out. Long sequences of identically typed parameters can cause subtle bugs. If the client accidentally reverses two such parameters, the compiler won’t complain, but the program will misbehave at runtime (Item 51).

简而言之，可伸缩的构造器模式可以使用，但当它有很多参数时很难编写客户端代码，并且更难使人读懂。看到这些值的意思时读者会感到疑惑且必须很小心的数参数的个数而找到对应的构造器。一长串相同的类型参数可能导致细微的漏洞。如果客户端突然改变两个这样的参数，编译器并不会报错，但程序将会在运行时出错。

A second alternative when you’re faced with many optional parameters in a constructor is the *JavaBeans* pattern, in which you call a parameterless constructor to create the object and then call setter methods to set each required parameter and each optional parameter of interest:

当你在构造器里遇到许多可选参数时，还有一个方法就是JavaBeans模式，此模式你屌用一个无参构造函数创建对象，然后用setter方法给每一个需要的参数和可选参数设值

```java
public class NutritionFacts {
	private  int servingSize = -1;   
	private  int servings = -1;
	private  int calories = 0;
	private  int fat = 0;
	private  int sodium = 0;
	private  int carbohydrate = 0;  
	public NutritionFacts() { } 
	public void setServingSize(int servingSize) {
		this.servingSize = servingSize;
	} 
	public void setServings(int servings) {
		this.servings = servings;
	} 
	public void setCalories(int calories) {
		this.calories = calories;
	} 
	public void setFat(int fat) {
		this.fat = fat;
	} 
	public void setSodium(int sodium) {
		this.sodium = sodium;
	} 
	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
}
```

This pattern has none of the disadvantages of the telescoping constructor pattern. It is easy, if a bit wordy, to create instances, and easy to read the resulting code:

这个模式没有伸缩构造器模式的缺点，它很简单，但创造实例有点啰嗦，且最终代码容易读

```java
		NutritionFacts cocaCola = new NutritionFacts(); 
		cocaCola.setServingSize(240); 
		cocaCola.setServings(8); 
		cocaCola.setCalories(100); 
		cocaCola.setSodium(35); 
		cocaCola.setCarbohydrate(27);
```

Unfortunately, the JavaBeans pattern has serious disadvantages of its own. Because construction is split across multiple calls, **a JavaBean may be in an inconsistent state partway through its construction.** The class does not have the option of enforcing consistency merely by checking the validity of the constructor parameters. Attempting to use an object when it’s in an inconsistent state may cause failures that are far removed from the code containing the bug and hence difficult to debug. A related disadvantage is that **the JavaBeans pattern precludes the possibility of making a class immutable** (Item 17) and requires added effort on the part of the programmer to ensure thread safety.

不幸的是，JavaBeans模式自身有严重缺点。因为构造器带多次调用中被分割，在构造过程中一个JavaBean可能是一个不一致的的状态。这个类没有通过检查有效构造器参数的强制一致性选项，在一个没有一致性状态下企图使用一个对象，可能导致失败使得代码中的bug很难被排查且更难去debug。同时带来的缺点是JavaBeans模式不许将这个类设置成不可变类。且需要程序员花费更多努力在线程安全问题上面。

It is possible to reduce these disadvantages by manually “freezing” the object when its construction is complete and not allowing it to be used until frozen, but this variant is unwieldy and rarely used in practice. Moreover, it can cause errors at runtime because the compiler cannot ensure that the programmer calls the freeze method on an object before using it.

手工冻结这个类在构造完成前，且在冻结结束前不允许被叫用去减少这些缺点是可能的，但这个变量是很笨重的，且很难应用在实践中。而且，这可以在运行的是时候导致错误，因为在一个对象使用前编译器不能确定程序员调用这个解冻的方法

Luckily, there is a third alternative that combines the safety of the telescoping constructor pattern with the readability of the JavaBeans pattern. It is a form of the *Builder* pattern [Gamma95]. Instead of making the desired object directly, the client calls a constructor (or static factory) with all of the required parameters and gets a *builder object*. Then the client calls setter-like methods on the builder object to set each optional parameter of interest. Finally, the client calls a parameterless build method to generate the object, which is typically immutable. The builder is typically a static member class (Item 24) of the class it builds. Here’s how it looks in practice:

幸运的是有第三种解决方法可以结合伸缩构造模式的安全性以及JavaBeans模式的可读性。它是Builder模式的一种形式，客户端调用一个构造器（或者静态工厂）到所有所需参数后获得一个builder对象去代替之前直接获取所需队形。然后客户端在builder对象上调用类似setter的方法去设置每个可选择参数和得到一个builder对象。

```java
public class NutritionFacts {
	private  int servingSize = -1;   
	private  int servings = -1;
	private  int calories = 0;
	private  int fat = 0;
	private  int sodium = 0;
	private  int carbohydrate = 0;  
	
	  public static class Builder {
	        private final int servingSize;
	        private final int servings;
	        private int calories = 0;
	        private int fat = 0;
	        private int sodium = 0;
	        private int carbohydrate = 0;
	        public Builder(int servingSize, int servings) { 
	        	this.servingSize = servingSize; 
	        	this.servings = servings;
	        }
	        public Builder calories(int val)
	        { calories = val; return this; }
	        public Builder fat(int val)
	        { fat = val; return this; }
	        public Builder sodium(int val)
	        { sodium = val; return this; }
	        public Builder carbohydrate(int val)
	        { carbohydrate = val; return this; }
	            public NutritionFacts build() {
	                return new NutritionFacts(this);
	        } 
	  }
	  private NutritionFacts(Builder builder) {
		  servingSize = builder.servingSize;
		  servings = builder.servings;
		  calories = builder.calories;
		  fat = builder.fat;
		  sodium = builder.sodium;
		  carbohydrate = builder.carbohydrate; 
	  }
}

```

The NutritionFacts class is immutable, and all parameter default values are in one place. The builder’s setter methods return the builder itself so that invocations can be chained, resulting in a *fluent API*. Here’s how the client code looks:

这个NutritionFacts类是不可改变的，且所有的参数默认值都在一起，builder的setter方法返回这个builder自己，所以调用就可以被链接起来，返回一个流畅的API，以下是客户端代码的示例：

```java
 NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8) .calories(100).sodium(35).carbohydrate(27).build();
```

This client code is easy to write and, more importantly, easy to read. **The Builder pattern simulates named optional parameters** as found in Python and Scala.

这个客户端代码容易编写，而且更重要的是容易读。这个Builder模式模仿Python和Scala中的可选参数

Validity checks were omitted for brevity. To detect invalid parameters as soon as possible, check parameter validity in the builder’s constructor and methods. Check invariants involving multiple parameters in the constructor invoked by the build method. To ensure these invariants against attack, do the checks on object fields after copying parameters from the builder (Item 50). If a check fails, throw an IllegalArgumentException (Item 72) whose detail message indicates which parameters are invalid (Item 75).

为了简洁，省略了有效检查。为了尽可能快的找到无用参数，检查参数在builder的构造器和方法中的有效参数。检查不变性需要被build 方法调用的构造器中的多个参数。为了确保这些不变性不被攻击，在从builder复制参数之后检查对象的属性。如果检查不通过抛出一个IllegalArgumentException  上面有哪个参数无效的详细信息

**The Builder pattern is well suited to class hierarchies.** Use a parallel hierarchy of builders, each nested in the corresponding class. Abstract classes have abstract builders; concrete classes have concrete builders. For example, consider an abstract class at the root of a hierarchy representing various kinds of pizza:

Builder模式非常适合类层次结构。使用一个构建者的平行层次结构，每个潜逃在相应的类中。抽象类有抽象builder；具体类有具体的builder。例如，想象代表各种比萨饼的根层次结构的抽象类：

```java
// Builder pattern for class hierarchies
import java.util.EnumSet; 
import java.util.Objects; 
import java.util.Set;

public abstract class Pizza {
	public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
	
	abstract static class Builder<T extends Builder<T>> { 
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
    final Set<Topping> toppings;
		public T addTopping(Topping topping) {
    toppings.add(Objects.requireNonNull(topping)); 
    return self();
        }
        abstract Pizza build();
    // Subclasses must override this method to return "this"
        protected abstract T self();
    }
    Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone(); // See Item 50
    } }
```

Note that Pizza.Builder is a *generic type* with a *recursive type parameter* (Item 30). This, along with the abstract self method, allows method chaining to work properly in subclasses, without the need for casts. This workaround for the fact that Java lacks a self type is known as the *simulated self-type* idiom.

注意这个Pizza Builder 是一个带一个递归类型参数的泛型类型，与抽象方法一起，允许方法链尽可能的在子类工作。























