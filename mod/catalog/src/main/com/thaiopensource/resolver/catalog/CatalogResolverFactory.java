package com.thaiopensource.resolver.catalog;

import java.util.List;

import com.thaiopensource.resolver.Resolver;

public interface CatalogResolverFactory {
	Resolver newInstance(List<String> catalogUris);
}
