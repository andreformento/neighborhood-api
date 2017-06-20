package com.formento.neighborhood.service;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import com.formento.neighborhood.service.impl.RectangleServiceDefault;
import com.google.common.collect.ImmutableList;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class RectangleServiceTest {

    private RectangleService rectangleService;
    private Point point;
    private Collection<Rectangle> rectanglesByPoint;

    private List<Rectangle> rectanglesReader(final DataTable dataTable) {
        final ImmutableList.Builder<Rectangle> rectanglesBuilder = ImmutableList.builder();
        for (List<String> fields : dataTable.raw()) {
            rectanglesBuilder.add(new Rectangle(
                new Point(Integer.valueOf(fields.get(0)), Integer.valueOf(fields.get(1))),
                new Point(Integer.valueOf(fields.get(2)), Integer.valueOf(fields.get(3)))
            ));
        }
        return rectanglesBuilder.build();
    }

    @Given("^the region with this rectangles:$")
    public void given_a_valid_region(final DataTable dataTable) throws IOException {
        final List<Rectangle> rectangles = rectanglesReader(dataTable);
        rectangleService = new RectangleServiceDefault(rectangles);
    }

    @And("^a point \\((\\d+),[\\w\\s]?(\\d+)\\)$")
    public void and_a_point(final Integer x, final Integer y) {
        point = new Point(x, y);
    }

    @When("^get rectangles by point$")
    public void when_get_rectangles_by_point() {
        rectanglesByPoint = rectangleService.findRectanglesByPoint(point);
    }

    @Then("^should be exist exactly (\\d+) rectangles$")
    public void should_be_exist_exactly_n_rectangles(final Integer quantityOfRectangles) {
        assertThat(rectanglesByPoint, is(notNullValue()));
        assertThat(rectanglesByPoint.size(), is(equalTo(quantityOfRectangles)));
    }

    @And("^should have this rectangles:$")
    public void should_have_this_rectangles(final DataTable dataTable) {
        final List<Rectangle> expectedRectangles = rectanglesReader(dataTable);
        assertThat(rectanglesByPoint, is(notNullValue()));
        testList(rectanglesByPoint, expectedRectangles);
    }

    private void testList(Collection<?> list1, Collection<?> list2) {
        assertThat(list1.size(), equalTo(list2.size()));
        list1.forEach(item -> assertThat(list2.contains(item), is(true)));
    }

}
