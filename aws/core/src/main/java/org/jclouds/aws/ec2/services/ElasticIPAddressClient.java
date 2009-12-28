/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
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
package org.jclouds.aws.ec2.services;

import java.net.InetAddress;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

import org.jclouds.aws.AWSResponseException;
import org.jclouds.aws.ec2.domain.PublicIpInstanceIdPair;
import org.jclouds.concurrent.Timeout;

/**
 * Provides access to EC2 via their REST API.
 * <p/>
 * 
 * @author Adrian Cole
 */
@Timeout(duration = 30, timeUnit = TimeUnit.SECONDS)
public interface ElasticIPAddressClient {

   /**
    * Acquires an elastic IP address for use with your account.
    * 
    * @see #describeAddresses
    * @see #releaseAddress
    * @see #associateAddress
    * @see #disassociateAddress
    * @see <a href="http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/ApiReference-query-AllocateAddress.html"
    */
   InetAddress allocateAddress();

   /**
    * Associates an elastic IP address with an instance. If the IP address is currently assigned to
    * another instance, the IP address is assigned to the new instance. This is an idempotent
    * operation. If you enter it more than once, Amazon EC2 does not return an error.
    * 
    * @param publicIp
    *           IP address that you are assigning to the instance.
    * @param instanceId
    *           The instance to associate with the IP address.
    * 
    * @see #allocateAddress
    * @see #describeAddresses
    * @see #releaseAddress
    * @see #disassociateAddress
    * @see <a href="http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/index.html?ApiReference-query-AssociateAddress.html"
    */
   void associateAddress(InetAddress publicIp, String instanceId);

   /**
    * Disassociates the specified elastic IP address from the instance to which it is assigned. This
    * is an idempotent operation. If you enter it more than once, Amazon EC2 does not return an
    * error.
    * 
    * @param publicIp
    *           IP address that you are assigning to the instance.
    * 
    * @see #allocateAddress
    * @see #describeAddresses
    * @see #releaseAddress
    * @see #associateAddress
    * @see <a href="http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/index.html?ApiReference-query-DisdisassociateAddress.html"
    */
   void disassociateAddress(InetAddress publicIp);

   /**
    * Releases an elastic IP address associated with your account.
    * 
    * @param publicIp
    *           The IP address that you are releasing from your account.
    * @see #allocateAddress
    * @see #describeAddresses
    * @see #associateAddress
    * @see #disassociateAddress
    * @see <a href="http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/index.html?ApiReference-query-ReleaseAddress.html"
    */
   void releaseAddress(InetAddress publicIp);

   /**
    * Lists elastic IP addresses assigned to your account or provides information about a specific
    * address.
    * 
    * @param publicIps
    *           Elastic IP address to describe.
    * @throws AWSResponseException
    *            if the requested publicIp is not found
    * @see #allocateAddress
    * @see #releaseAddress
    * @see <a href="http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/ApiReference-query-DescribeAddresses.html"
    *      />
    */
   SortedSet<PublicIpInstanceIdPair> describeAddresses(InetAddress... publicIps);

}