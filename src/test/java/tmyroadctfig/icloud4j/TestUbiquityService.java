/*
 *    Copyright 2016 Luke Quinane
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tmyroadctfig.icloud4j;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static tmyroadctfig.icloud4j.ICloudTestUtils.getServiceFromSystemProperties;

/**
 * Tests for {@link UbiquityService}.
 */
public class TestUbiquityService
{
    @Test
    public void testListItems()
    {
        // Arrange
        ICloudService iCloudService = getServiceFromSystemProperties();
        UbiquityService ubiquityService = new UbiquityService(iCloudService);

        // Act
        UbiquityNode root = ubiquityService.getRoot();
        List<UbiquityNode> children = root.getChildren();

        // Assert
        assertThat(children.isEmpty(), is(false));
    }
}