package com.tapestry.views;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.tapestry.security.SecurityService;
// import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.about.AboutView;
import com.tapestry.views.dashboard.DashboardView;
import com.tapestry.views.settings.SettingsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

	private final SecurityService securityService;
	private H2 viewTitle;

	public MainLayout(SecurityService securityService) {
		this.securityService = securityService;

		this.setPrimarySection(Section.DRAWER);
		this.addDrawerContent();
		this.addHeaderContent();
	}

	private void addHeaderContent() {
		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel("Menu toggle");

		this.viewTitle = new H2();
		this.viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		this.addToNavbar(true, toggle, this.viewTitle);
	}

	private void addDrawerContent() {
		H1 appName = new H1("Tapestry");
		appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		Header header = new Header(appName);

		Scroller scroller = new Scroller(this.createNavigation());

		this.addToDrawer(header, scroller, this.createFooter());
	}

	private SideNav createNavigation() {
		SideNav nav = new SideNav();

		nav.addItem(new SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create()));
		nav.addItem(new SideNavItem("Settings", SettingsView.class, VaadinIcon.COGS.create()));
		nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();

		// if (this.authenticatedUser.get().isPresent()) {
		// Avatar avatar = new Avatar(this.getTapestryUser().getFirstName());
		// Avatar avatar = new
		// Avatar(this.authenticatedUser.get().get().getFirstName());

		// StreamResource resource = new StreamResource("profile-pic", () -> new
		// ByteArrayInputStream(user.getProfilePicture()));
		// avatar.setImageResource(resource);
		// avatar.setThemeName("xsmall");
		// avatar.getElement().setAttribute("tabindex", "-1");

		MenuBar userMenu = new MenuBar();
		userMenu.setThemeName("tertiary-inline contrast");

		MenuItem userName = userMenu.addItem("");
		Div div = new Div();
		// div.add(avatar);
		// div.add(this.authenticatedUser.get().get().getFirstName());
		div.add(new Icon("lumo", "dropdown"));
		div.getElement().getStyle().set("display", "flex");
		div.getElement().getStyle().set("align-items", "center");
		div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
		userName.add(div);

		userName.getSubMenu().addItem("Toggle Theme", e -> {
			// Get the current UI instance
			UI ui = UI.getCurrent();

			// Check if the Lumo dark theme is already applied
			if (ui.getElement().getThemeList().contains("dark")) {
				// If the dark theme is applied, remove it to switch to light mode
				ui.getElement().getThemeList().remove("dark");
			} else {
				// If the dark theme is not applied, add it to switch to dark mode
				ui.getElement().getThemeList().add("dark");
			}
		});

		userName.getSubMenu().addItem("Sign out", e -> {
			this.securityService.logout();
		});

		layout.add(userMenu);
		// } else {
		// Anchor loginLink = new Anchor("login", "Sign in");
		// layout.add(loginLink);
		// }

		return layout;
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
}
