package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class WarehouseApiTest extends ApiSupport {
    final String WAREHOUSE_NAME = "Yellow Duck House";
    final String UPDATE_WAREHOUSE_NAME = "Yellow Chicken House";
    final String WAREHOUSE_URL_KEY = "warehouse_url";
    final String WAREHOUSE_BASE_URL = "/wareHouses";

    final Map<String, Object> WAREHOUSE_INFO = new HashMap<String, Object>() {{
        put("name", WAREHOUSE_NAME);
    }};
    final Map<String, Object> UPDATE_WAREHOUSE_INFO = new HashMap<String, Object>() {{
        put("name", UPDATE_WAREHOUSE_NAME);
        put("containers", Arrays.asList(1, 2));
    }};

    @Test
    public void should_success_to_create_warehouse() throws Exception {
        Response response = post(WAREHOUSE_BASE_URL, WAREHOUSE_INFO);
        assertThat(response.getStatus(), is(201));
        int firstCreatedId = Integer.parseInt(response.readEntity(Map.class).get(WAREHOUSE_URL_KEY).toString().split("/")[1]);

        response = post(WAREHOUSE_BASE_URL, WAREHOUSE_INFO);
        assertThat(response.getStatus(), is(201));
        int secondCreatedId = Integer.parseInt(response.readEntity(Map.class).get(WAREHOUSE_URL_KEY).toString().split("/")[1]);
        assertThat(secondCreatedId, is(firstCreatedId + 1));
    }

    @Test
    public void should_success_to_view_all_warehouses() throws Exception {
        post(WAREHOUSE_BASE_URL, WAREHOUSE_INFO);

        Response response = get(WAREHOUSE_BASE_URL);
        assertThat(response.getStatus(), is(200));

        Map responseEntity = response.readEntity(Map.class);
        assertThat(responseEntity.get("totalCount"), is(1));
        assertThat(((Map)((List)responseEntity.get("providers")).get(0)).get("name"), is(WAREHOUSE_NAME));
    }

    @Test
    public void should_404_to_view_an_inexist_warehouse() throws Exception {
        Response response = get(WAREHOUSE_BASE_URL + "/1");
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_view_an_existing_warehouse() throws Exception {
        Response response = post(WAREHOUSE_BASE_URL, WAREHOUSE_INFO);

        String location = response.readEntity(Map.class).get(WAREHOUSE_URL_KEY).toString();

        response = get(location);
        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("name"), is(WAREHOUSE_NAME));
    }

    @Test
    public void should_404_to_update_an_inexist_warehouse() throws Exception {
        Response response = put(WAREHOUSE_BASE_URL + "/1", WAREHOUSE_INFO);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_update_an_existing_warehouse() throws Exception {
        Response response = post(WAREHOUSE_BASE_URL, WAREHOUSE_INFO);

        String location = response.readEntity(Map.class).get(WAREHOUSE_URL_KEY).toString();

        response = put(location, UPDATE_WAREHOUSE_INFO);
        assertThat(response.getStatus(), is(204));

        response = get(location);
        assertThat(response.getStatus(), is(200));
        Map responseEntity = response.readEntity(Map.class);
        assertThat(responseEntity.get("name"), is(UPDATE_WAREHOUSE_NAME));
        assertThat(responseEntity.get("containers"), is(Arrays.asList(1,2)));
    }

    @Test
    public void should_404_to_delete_an_inexist_warehouse() throws Exception {
        Response response = delete(WAREHOUSE_BASE_URL + "/1");
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_delete_an_existing_warehouse() throws Exception {
        Response response = post(WAREHOUSE_BASE_URL, WAREHOUSE_INFO);

        String location = response.readEntity(Map.class).get(WAREHOUSE_URL_KEY).toString();

        response = delete(location);
        assertThat(response.getStatus(), is(204));

        response = get(location);
        assertThat(response.getStatus(), is(404));
    }
}
