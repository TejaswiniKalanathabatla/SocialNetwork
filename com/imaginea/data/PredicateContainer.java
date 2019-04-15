package com.imaginea.data;

import java.util.function.Predicate;

public class PredicateContainer {
	public final static Predicate<String> STRING_EMPTY_CHECK_PREDICATE = (String s) -> null == s || s.trim().isEmpty();

	public Predicate<String> stringEmptyCheckPredicate() {
		return STRING_EMPTY_CHECK_PREDICATE;
	}

}
