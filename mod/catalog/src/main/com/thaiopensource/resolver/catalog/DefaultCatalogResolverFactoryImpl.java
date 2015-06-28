package com.thaiopensource.resolver.catalog;

import java.util.List;

import com.thaiopensource.resolver.Resolver;

public class DefaultCatalogResolverFactoryImpl implements CatalogResolverFactory {

	@Override
	public Resolver newInstance(List<String> catalogUris) {
		return new CatalogResolver(catalogUris);
	}

}
