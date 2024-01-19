package com.tapestry.views.common.cards.user;

import com.tapestry.data.entity.UserOld;
import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.HorizontalLayoutBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.ImageBuilder;
import com.tapestry.views.common.builders.VerticalLayoutBuilder;
import com.tapestry.views.common.views.TapestryCardSkeleton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.Background;
import com.vaadin.flow.component.charts.model.BackgroundShape;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataLabels;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.Pane;
import com.vaadin.flow.component.charts.model.PlotOptionsSolidgauge;
import com.vaadin.flow.component.charts.model.YAxis;

public class UserSummaryCard extends TapestryCardSkeleton<UserOld>
{
	public UserSummaryCard(final UserOld source, AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(source, authenticatedUser, repository);
	}

	@Override
	public Component getContent()
	{
		this.addClassName("user-summary-card");
		this.setSpacing(false);

		// The user photo
		final var userPhoto = ImageBuilder.create().url(this.getSource().getProfilePictureUrl(), "photo").className("profile-image").build();

		// The user's name
		final var name = HtmlLabelBuilder.create().large().selfCenter().className("title").text(this.getSource().getFullName()).build();

		// Their education progress
		if (this.getSource().isParent())
		{
			/* @formatter:off */

			return HorizontalLayoutBuilder.create().tight()
					.add( userPhoto )
					.add( VerticalLayoutBuilder.create().tight().add( name ).build() )
				.build();

			/* @formatter:on */
		}
		else
		{
			final var chart = this.buildEducationProgress();
			/* @formatter:off */

			return HorizontalLayoutBuilder.create().tight()
					.add( userPhoto )
					.add( VerticalLayoutBuilder.create().tight().add( name, chart ).build() )
				.build();

			/* @formatter:on */
		}

	}

	private Chart buildEducationProgress()
	{
		Chart chart = new Chart(ChartType.SOLIDGAUGE);
		chart.addClassName("educational-progress-gauge");

		// chart.setWidth("250px");
		// chart.setHeight("200px");

		Configuration configuration = chart.getConfiguration();

		Pane pane = configuration.getPane();
		pane.setCenter(new String[]
		{ "50%", "50%" });
		pane.setStartAngle(-90);
		pane.setEndAngle(90);

		Background paneBackground = new Background();
		paneBackground.setInnerRadius("60%");
		paneBackground.setOuterRadius("100%");
		paneBackground.setShape(BackgroundShape.ARC);
		pane.setBackground(paneBackground);

		YAxis yAxis = configuration.getyAxis();
		yAxis.setTickAmount(2);
		yAxis.setTitle("Educational Progress");
		yAxis.setMinorTickInterval("null");
		yAxis.getTitle().setY(-50);
		yAxis.getLabels().setY(16);
		yAxis.setMin(0);
		yAxis.setMax(100);

		PlotOptionsSolidgauge plotOptionsSolidgauge = new PlotOptionsSolidgauge();

		DataLabels dataLabels = plotOptionsSolidgauge.getDataLabels();
		dataLabels.setY(5);
		dataLabels.setUseHTML(true);

		configuration.setPlotOptions(plotOptionsSolidgauge);

		DataSeries series = new DataSeries("Educational Progress");

		DataSeriesItem item = new DataSeriesItem();
		item.setY(this.getSource().getEducationalProgress());

		DataLabels dataLabelsSeries = new DataLabels();
		dataLabelsSeries.setFormat("<div style=\"text-align:center\"><span style=\"font-size:25px;" + "color:black' + '\">{y}</span><br/></div>");
		// dataLabelsSeries.setFormat("<div style=\"text-align:center\"><span style=\"font-size:25px;" + "color:black' + '\">{y}</span><br/>" + "<span style=\"font-size:12px;color:silver\">km/h</span></div>");

		item.setDataLabels(dataLabelsSeries);

		series.add(item);

		configuration.addSeries(series);

		return chart;
	}
}
