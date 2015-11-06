package cn.edu.jlu.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 函数式编程操作容器
 * 主要有下面操作：
 * 1.迭代容器元素
 * 2.转换容器
 * 3.过滤容器元素
 * 4.选择元素
 * 5.reduce
 * @author bean
 *
 */
public class Collections {
	
	private static Predicate<String> startWith(final String s){//即便这个地方不标注final，s在函数体中也不能被更改
		return name -> name.startsWith(s);
	}
	
	
	public static void main(String[] args) {
		List<String> list  = Arrays.asList("haha", "hehe", "heihei", "xixi", "gaga");
		// 迭代操作
		// 1.Consumer
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		// 2.Lambda Expression
		list.forEach(s -> System.out.println(s));
		// 3.method reference
		list.forEach(System.out::println);
		// 转换操作
		list.stream().map(s -> s.length()).forEach(System.out::println);
		List<Integer> length = list.stream().map(s -> s.length()).collect(Collectors.toList());
		
		// 过滤操作
		final Predicate<String> p = name -> name.startsWith("h");
		list.stream().filter(p).forEach(System.out::println);
		list.stream().filter(startWith("x")).forEach(System.out::println);
		
		final Function<String, Predicate<String>> start = letter -> {
			return name -> name.startsWith(letter);
		};
		list.stream().filter(start.apply("x")).forEach(System.out::println);
		final Function<String, Predicate<String>> start2 = letter -> name -> name.startsWith(letter);
		list.stream().filter(start2.apply("g")).forEach(System.out::println);
		// 选择元素
		final Optional<String> opt = list.stream().filter(name -> name.startsWith("s")).findFirst();
		System.out.println(opt.orElse("hehe"));
		// reduce
		list.stream().mapToInt(name -> name.length()).sum();
		String longest = list.stream().reduce((name1, name2) -> name1.length() > name2.length() ? name1 : name2).get();
		System.out.println(longest);
		// join element
		System.out.println(String.join(", ", list));
		System.out.println(list.stream().map(name -> name.toUpperCase()).collect(Collectors.joining(", ")));
	}
	
}
