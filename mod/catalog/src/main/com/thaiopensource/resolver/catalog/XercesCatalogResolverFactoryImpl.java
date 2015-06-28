package com.thaiopensource.resolver.catalog;

import java.util.List;

import org.apache.xml.resolver.CatalogManager;
import org.apache.xml.resolver.tools.CatalogResolver;

import com.thaiopensource.resolver.Resolver;
import com.thaiopensource.resolver.SequenceResolver;
import com.thaiopensource.resolver.xml.sax.SAX;
import com.thaiopensource.resolver.xml.transform.Transform;

public class XercesCatalogResolverFactoryImpl implements CatalogResolverFactory {

	@Override
	public Resolver newInstance(List<String> catalogUris) {
		CatalogManager catalogManager = new CatalogManager();
		catalogManager.setIgnoreMissingProperties(true);
		catalogManager.setRelativeCatalogs(true);
		catalogManager.setVerbosity(100);
		catalogManager.setCatalogFiles(String.join(";", catalogUris));

		CatalogResolver resolver = new CatalogResolver(catalogManager);
		return new SequenceResolver(SAX.createResolver(resolver, false),
				Transform.createResolver(resolver));
	}

}
