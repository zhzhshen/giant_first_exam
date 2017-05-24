package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class ContainerApiTest extends ApiSupport {
    final int CONTAINER_ID = 4;
    final int UPDATE_CONTAINER_ID = 5;
    final String CONTAINER_URL_KEY = "container_url";
    final String CONTAINER_BASE_URL = "/containers";

    final Map<String, Object> CONTAINER_INFO = new HashMap<String, Object>() {{
        put("id", CONTAINER_ID);
    }};
    final Map<String, Object> UPDATE_CONTAINER_INFO = new HashMap<String, Object>() {{
        put("id", UPDATE_CONTAINER_ID);
    }};

    @Test
    public void should_success_to_create_container() throws Exception {
        Response response = post(CONTAINER_BASE_URL, CONTAINER_INFO);
        assertThat(response.getStatus(), is(201));
        int firstCreatedId = Integer.parseInt(response.readEntity(Map.class).get(CONTAINER_URL_KEY).toString().split("/")[1]);

        response = post(CONTAINER_BASE_URL, CONTAINER_INFO);
        assertThat(response.getStatus(), is(201));
        int secondCreatedId = Integer.parseInt(response.readEntity(Map.class).get(CONTAINER_URL_KEY).toString().split("/")[1]);
        assertThat(secondCreatedId, is(firstCreatedId + 1));
    }

    @Test
    public void should_success_to_view_all_containers() throws Exception {
        post(CONTAINER_BASE_URL, CONTAINER_INFO);

        Response response = get(CONTAINER_BASE_URL);
        assertThat(response.getStatus(), is(200));

        Map responseEntity = response.readEntity(Map.class);
        assertThat(responseEntity.get("totalCount"), is(1));
        assertThat(((Map)((List)responseEntity.get("containers")).get(0)).get("id"), is(CONTAINER_ID));
    }

    @Test
    public void should_404_to_view_inexist_container() throws Exception {
        Response response = get(CONTAINER_BASE_URL + CONTAINER_ID);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_view_an_existing_container() throws Exception {
        Response response = post(CONTAINER_BASE_URL, CONTAINER_INFO);
        int containerId = Integer.parseInt(response.readEntity(Map.class).get(CONTAINER_URL_KEY).toString().split("/")[1]);

        response = get(CONTAINER_BASE_URL + "/" + containerId);
        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("id"), is(CONTAINER_ID));
    }

    @Test
    public void should_404_to_update_an_inexist_container() throws Exception {
        Response response = put(CONTAINER_BASE_URL + "/" + CONTAINER_ID, UPDATE_CONTAINER_INFO);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_update_an_existing_container() throws Exception {
        Response response = post(CONTAINER_BASE_URL, CONTAINER_INFO);
        int containerId = Integer.parseInt(response.readEntity(Map.class).get(CONTAINER_URL_KEY).toString().split("/")[1]);

        response = put(CONTAINER_BASE_URL + "/" + containerId, UPDATE_CONTAINER_INFO);
        assertThat(response.getStatus(), is(204));
        response = get(CONTAINER_BASE_URL + "/" + containerId);
        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("id"), is(UPDATE_CONTAINER_ID));
    }

    @Test
    public void should_404_to_delete_an_inexist_container() throws Exception {
        Response response = delete(CONTAINER_BASE_URL + "/" + CONTAINER_ID);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_delete_an_existing_container() throws Exception {
        Response response = post(CONTAINER_BASE_URL, CONTAINER_INFO);
        int containerId = Integer.parseInt(response.readEntity(Map.class).get(CONTAINER_URL_KEY).toString().split("/")[1]);

        response = delete(CONTAINER_BASE_URL + "/" + containerId);
        assertThat(response.getStatus(), is(204));
        response = get(CONTAINER_BASE_URL + "/" + containerId);
        assertThat(response.getStatus(), is(404));
    }
}

