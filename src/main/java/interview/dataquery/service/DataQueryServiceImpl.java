package interview.dataquery.service;

import interview.dataquery.api.DataQueryService;
import interview.dataquery.api.Item;
import interview.dataquery.api.QueryParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//https://regex101.com/

public class DataQueryServiceImpl implements DataQueryService {

	Map<String, Item> store = new HashMap<>();

	public void save (Item item) throws Exception {
		if (store.containsKey(item.getId())) {
			System.out.println("ID should be unique, override the store for the given id");
		}
		store.put(item.getId(), item);
	}

	public List<Item> query (String query) throws Exception {
		if (query.isEmpty()) {
			return new ArrayList(store.values());
		}

		Pattern operation = Pattern.compile("(AND|NOT|OR)");
		Matcher matcher1 = operation.matcher(query);

		Pattern base = Pattern.compile("([A-Z_]+)(\\()([a-z A-z]+)(,+\\s|,)(\"[a-z A-Z]+\"|\"[a-z A-Z]+[0-9]\"|[0-9]+)(\\))");
		Matcher matcher2 = base.matcher(query);

		List<Item> items = new ArrayList(store.values());

		if (matcher1.find()) {
			items = findMatches(matcher2, items, matcher1.group(1));
		} else {
			items = findMatches(matcher2, items, null);
		}

		return items;
	}

	private List<Item> findMatches (Matcher matcher, List<Item> items, String operator) throws QueryParseException {
		boolean find = false;

		if (operator == null || !operator.equals("OR")) {
			List<Item> clonedItems = new ArrayList<>(items);
			while (matcher.find()) {
				find = true;
				switch (matcher.group(1)) {
					case "EQUAL":
						clonedItems = equal(matcher.group(3), matcher.group(5), clonedItems);
						break;
					case "LESS_THAN":
						clonedItems = lessThan(matcher.group(3), Integer.parseInt(matcher.group(5)), clonedItems);
						break;
					case "GREATER_THAN":
						clonedItems = greaterThan(matcher.group(3), Integer.parseInt(matcher.group(5)), clonedItems);
						break;
				}
			}

			if (!find && operator == null) {
				throw new QueryParseException("Invalid Query Passed", null);
			}

			if (operator != null && operator.equals("NOT")) {
				items.removeAll(clonedItems);
				return items;
			}
			return clonedItems;
		} else {
			List<Item> clonedItems = new ArrayList<>();
			while (matcher.find()) {
				find = true;
				switch (matcher.group(1)) {
					case "EQUAL":
						clonedItems.addAll(equal(matcher.group(3), matcher.group(5), items));
						break;
					case "LESS_THAN":
						clonedItems.addAll(lessThan(matcher.group(3), Integer.parseInt(matcher.group(5)), items));
						break;
					case "GREATER_THAN":
						clonedItems.addAll(greaterThan(matcher.group(3), Integer.parseInt(matcher.group(5)), items));
						break;
				}
			}

			if (!find) {
				throw new QueryParseException("Invalid Query Passed", null);
			}

			Set<Item> set = new HashSet<>(clonedItems);
			return new ArrayList<>(set);
		}
	}

	private List<Item> equal (String property, String value, List<Item> items) {
		return items.stream()
					   .filter(item -> item.getProperty(property).equals(value.replace("\"", "")))
					   .collect(Collectors.toList());
	}

	private List<Item> lessThan (String property, int value, List<Item> items) {
		return items.stream()
					   .filter(item -> (int) item.getProperty(property) < value)
					   .collect(Collectors.toList());
	}

	private List<Item> greaterThan (String property, int value, List<Item> items) {
		return items.stream()
					   .filter(item -> (int) item.getProperty(property) > value)
					   .collect(Collectors.toList());
	}
}
