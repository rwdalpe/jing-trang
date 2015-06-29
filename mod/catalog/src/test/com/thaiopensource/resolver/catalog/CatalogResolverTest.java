/*
 * Copyright (c) 2001-2003 Thai Open Source Software Center Ltd
 * Copyright (c) 2015 Robert Winslow Dalpe
 * 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 *  Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *  Neither the name of the Thai Open Source Software Center Ltd nor
 *  the names of its contributors may be used to endorse or promote
 *  products derived from this software without specific prior written
 *  permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.thaiopensource.resolver.catalog;

import com.thaiopensource.resolver.Input;
import com.thaiopensource.resolver.Resolver;
import com.thaiopensource.resolver.ResolverException;
import com.thaiopensource.resolver.xml.ExternalEntityIdentifier;
import com.thaiopensource.resolver.xml.ExternalIdentifier;
import com.thaiopensource.resolver.xml.sax.SAXResolver;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests CatalogResolver.
 */
public class CatalogResolverTest {
	@Test
	public void testResolve() throws IOException, ResolverException {
		List<String> catalogs = new ArrayList<String>();
		catalogs.add(resourceUri("catalog.xml"));
		Resolver resolver = new CatalogResolver(catalogs, new SAXResolver(null));
		ExternalIdentifier xid = new ExternalIdentifier("foo.xml",
				"http://www.example.com/index.html", "The Great Foo");
		Input input = new Input();
		resolver.resolve(xid, input);
		Assert.assertEquals(input.getUri(), "http://www.example.com/bar.xml");
	}

	@Test
	public void testSystemResolve() throws IOException, ResolverException {
		List<String> catalogs = new ArrayList<String>();
		catalogs.add(resourceUri("catalog.xml"));
		Resolver resolver = new CatalogResolver(catalogs, new SAXResolver(null));
		ExternalIdentifier xid = new ExternalIdentifier(
				"foo.xml",
				"http://example.com/index.html", "The Big System");
		Input input = new Input();
		resolver.resolve(xid, input);
		Assert.assertEquals(input.getUri(), "http://www.example.com/system.xml");
	}

	static String resourceUri(String fileName) {
		String className = CatalogResolverTest.class.getName();
		int dotIndex = className.lastIndexOf('.');
		String resourceName = className.substring(0, dotIndex + 1).replace('.',
				'/')
				+ fileName;
		return CatalogResolverTest.class.getClassLoader()
				.getResource(resourceName).toString();
	}
}
