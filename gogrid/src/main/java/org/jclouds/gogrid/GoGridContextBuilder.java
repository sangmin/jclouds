/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 */
package org.jclouds.gogrid;

import java.util.List;
import java.util.Properties;

import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.ComputeServiceContextBuilder;
import org.jclouds.compute.internal.ComputeServiceContextImpl;
import org.jclouds.gogrid.config.GoGridComputeServiceContextModule;
import org.jclouds.gogrid.config.GoGridRestClientModule;
import org.jclouds.gogrid.config.internal.GoGridResolveImagesModule;

import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

/**
 * 
 * @author Adrian Cole
 */
public class GoGridContextBuilder extends
         ComputeServiceContextBuilder<GoGridAsyncClient, GoGridClient> {

   public GoGridContextBuilder(Properties props) {
      super(new TypeLiteral<GoGridAsyncClient>() {
      }, new TypeLiteral<GoGridClient>() {
      }, props);
   }

   protected void addClientModule(List<Module> modules) {
      modules.add(new GoGridRestClientModule());
   }

   @Override
   protected void addContextModule(List<Module> modules) {
      modules.add(new GoGridComputeServiceContextModule());
   }

   @Override
   protected void addImageResolutionModule() {
      modules.add(new GoGridResolveImagesModule());
   }

   @Override
   public ComputeServiceContext buildComputeServiceContext() {
      return this
               .buildInjector()
               .getInstance(
                        Key
                                 .get(new TypeLiteral<ComputeServiceContextImpl<GoGridAsyncClient, GoGridClient>>() {
                                 }));
   }
}
