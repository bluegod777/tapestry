package com.tapestry.views.common.components;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.tapestry.utils.ValidateUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardSwitcherLayout extends VerticalLayout
{

	private final Map<String, Component> componentList;
	private String activeCard;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public CardSwitcherLayout()
	{
		// Allocate the list
		// this.componentList = CollectionsUtil.newTreeMap("componentList", String.class);
		this.componentList = new TreeMap<String, Component>();

		// Init the components
		this.init();
	}

	protected void init()
	{
	}

	public Component getCurrentCard()
	{
		for (final Component comp : this.componentList.values())
		{
			if (comp.isVisible())
			{
				return comp;
			}
		}

		return null;
	}

	public String getCurrentCardName()
	{
		for (final String key : this.componentList.keySet())
		{
			if (this.componentList.get(key).isVisible())
			{
				return key;
			}
		}

		return null;
	}

	public void addCard(final String key, final Component card)
	{
		// put it in our list so that we can get it later
		this.addToComponentList(key, card);

		// Add the card to the controller
		this.add(card);

		// Initially, hide the card
		card.setVisible(false);
	}

	public Component getCard(final String key)
	{
		try
		{
			final Component card;

			synchronized (this.componentList)
			{
				card = this.componentList.get(key);
			}

			if (card == null)
			{
				CardSwitcherLayout.log.warn(String.format("Someone called PnlCardSwitcher.getCard( \"%s\") and we found no match. This is probably because there are no search options", key));
				CardSwitcherLayout.log.warn(String.format("Current keys are: %s", this.componentList.keySet()));
			}

			return card;
		}

		catch (final Exception e)
		{
			CardSwitcherLayout.log.error("Unable to find card : " + key);

			return null;
		}
	}

	public void removeCard(final String key)
	{
		this.remove(this.componentList.get(key));

		this.componentList.remove(key);

	}

	public void removeAllCards()
	{
		this.removeAll();

		this.componentList.clear();
	}

	public void showCard(final String key)
	{
		// Hide all the children
		this.getChildren().forEach(c -> c.setVisible(false));

		// Ge the card
		final Component card = this.getCard(key);

		if (card != null)
		{
			// Issue a card callback if we need to
			if (card instanceof ICardSwitcherNotifier)
			{
				((ICardSwitcherNotifier) card).cardSelected();
			}

			// We are going to be selecting a card, so
			// let go through all the cards and tell them
			// they are going to be unselected
			this.componentList.keySet().forEach(cardKey ->
			{
				// Get the real component
				final Component c = this.getCard(cardKey);

				// Issue a card callback if we need to but only if
				// the card is not the one we are about to show
				if ((c instanceof ICardSwitcherNotifier) && !cardKey.equalsIgnoreCase(key))
				{
					((ICardSwitcherNotifier) c).cardUnSelected();
				}
			});

			// Show the card
			card.setVisible(true);

			// Save off the active card
			this.activeCard = key;
		}
		else
		{
			CardSwitcherLayout.log.warn(String.format("Card was not found : [%s]", key));
		}
	}

	private void addToComponentList(final String key, final Component card)
	{
		synchronized (this.componentList)
		{
			this.componentList.put(key, card);
		}
	}

	public boolean isEmpty()
	{
		return this.componentList.isEmpty();
	}

	public String[] getKeys()
	{
		return this.componentList.keySet().toArray(new String[0]);
	}

	public List<String> getKeyList()
	{
		return Arrays.asList(this.getKeys());
	}

	public Component[] getCards()
	{
		return this.componentList.values().toArray(new Component[0]);
	}

	public boolean hasCard(final String key)
	{
		return this.componentList.keySet().stream().filter(k -> k.equalsIgnoreCase(key)).findAny().orElse(null) != null;
	}

	public String getActiveCard()
	{
		return this.activeCard;
	}

	public void setActiveCard(final String activeCard)
	{
		this.activeCard = activeCard;
	}

	public boolean isCardShowing(final String key)
	{
		if (ValidateUtils.isNotNullOrEmpty(this.activeCard))
		{
			return this.activeCard.equalsIgnoreCase(key);
		}

		return false;
	}

	public CardSwitcherLayout overFlowY(final String type)
	{
		this.getElement().getStyle().set("overflow-y", type);

		return this;
	}

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------

	public static interface ICardSwitcherNotifier
	{

		void cardSelected();

		void cardUnSelected();

	}

	public static interface ICardSwitcherRemoteControl
	{

		public void addTab(final String cardName, final Component component, final boolean isSelect);

		public void replaceTab(final String cardName, final Component component, final boolean isSelect);

		public void removeTab(final String cardName);

		public void addHiddenTab(final Component component);

		public void removeHiddenTab();

	}
}
