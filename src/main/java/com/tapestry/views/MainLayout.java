package com.tapestry.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

	private H2 viewTitle;

	public MainLayout() {
		this.addHeaderContent();
	}

	private void addHeaderContent() {
		this.viewTitle = new H2();
		this.viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		this.addToNavbar(true, this.viewTitle);
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		this.viewTitle.setText(this.getCurrentPageTitle());
	}

	private String getCurrentPageTitle() {
		PageTitle title = this.getContent().getClass().getAnnotation(PageTitle.class);
		return title == null ? "" : title.value();
	}

	// userName.getSubMenu().addItem("Toggle Theme", e -> {
	// // Get the current UI instance
	// UI ui = UI.getCurrent();

	// // Check if the Lumo dark theme is already applied
	// if (ui.getElement().getThemeList().contains("dark")) {
	// // If the dark theme is applied, remove it to switch to light mode
	// ui.getElement().getThemeList().remove("dark");
	// } else {
	// // If the dark theme is not applied, add it to switch to dark mode
	// ui.getElement().getThemeList().add("dark");
	// }
	// });

	// userName.getSubMenu().addItem("Sign out", e -> {
	// this.securityService.logout();
	// });
}
