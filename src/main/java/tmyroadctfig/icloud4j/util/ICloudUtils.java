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

package tmyroadctfig.icloud4j.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import tmyroadctfig.icloud4j.ICloudException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

/**
 * iCloud utilities.
 *
 * @author Luke Quinanne
 */
public class ICloudUtils
{
    /**
     * Parses a JSON response from the request.
     *
     * @param httpClient the HTTP client.
     * @param post the request.
     * @param responseClass the type of JSON object to parse the values into.
     * @param <T> the type to parse into.
     * @return the object.
     * @throws ICloudException if there was an error returned from the request.
     */
    public static <T> T parseJsonResponse(CloseableHttpClient httpClient, HttpPost post, Class<T> responseClass)
    {
        try
        {
            HttpResponse response = httpClient.execute(post);
            String rawResponseContent = new StringResponseHandler().handleResponse(response);

            try
            {
                return new Gson().fromJson(rawResponseContent, responseClass);
            }
            catch (JsonSyntaxException e1)
            {
                //noinspection unchecked
                Map<String, Object> errorMap = new Gson().fromJson(rawResponseContent, Map.class);
                throw new ICloudException(response, errorMap);
            }
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Parses a JSON response from the request.
     *
     * @param httpClient the HTTP client.
     * @param httpGet the request.
     * @param responseClass the type of JSON object to parse the values into.
     * @param <T> the type to parse into.
     * @return the object.
     * @throws ICloudException if there was an error returned from the request.
     */
    public static <T> T parseJsonResponse(CloseableHttpClient httpClient, HttpGet httpGet, Class<T> responseClass)
    {
        try
        {
            HttpResponse response = httpClient.execute(httpGet);
            String rawResponseContent = new StringResponseHandler().handleResponse(response);

            try
            {
                return new Gson().fromJson(rawResponseContent, responseClass);
            }
            catch (JsonSyntaxException e1)
            {
                //noinspection unchecked
                Map<String, Object> errorMap = new Gson().fromJson(rawResponseContent, Map.class);
                throw new ICloudException(response, errorMap);
            }
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }
}
