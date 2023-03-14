package com.blackgaryc.library.domain.category;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryNode implements Serializable {
    private final int id;
    private final String name;
    private List<CategoryNode> children = Collections.emptyList();

    public CategoryNode(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategoryNode(Category category, List<CategoryNode> children) {
        this(category);
        this.children = children;
    }

    private void setChildren(List<CategoryNode> children) {
        this.children = children;
    }

    public List<CategoryNode> getChildren() {
        return children;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CategoryNode list2Node(List<Category> categories, Integer rootId) {
        Map<Integer, Category> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, o -> o));
        Map<Integer, List<Category>> collectByParentId = categories.stream().collect(Collectors.groupingBy(Category::getParentId));
        if (rootId != null) {
            //检查id是否合法
            boolean res = collectByParentId.keySet().stream()
                    .filter(integer1 -> integer1 >= 0)
                    .toList().contains(rootId);
            //非法时置为null
            rootId = res ? rootId : null;
        }

        if (rootId == null) {
            //对于null会选择当前id最小值作为rootId
            rootId = collectByParentId.keySet().stream()
                    .filter(integer1 -> integer1 >= 0)
                    .min(Comparator.comparingInt(value -> value)).orElse(0);
        }
        return getCategoryNode(categoryMap, collectByParentId, rootId);//从root节点递归出整个tree
    }

    @NotNull
    private static CategoryNode getCategoryNode(Map<Integer, Category> categoryMap, Map<Integer, List<Category>> collectByParentId, Integer integer) {
        //创建当前节点
        Category category1 = categoryMap.get(integer);
        CategoryNode node = new CategoryNode(category1);
        //获取当前节点的孩子
        List<Category> categories = collectByParentId.get(integer);
        if (Objects.isNull(categories)) {
            return node;
        }
        //处理孩子节点
        List<CategoryNode> category = categories.stream()
                //过滤掉没有父节点的
                .filter(category2 -> category2.getParentId() >= 0)
                //对子节点进行处理
                .map(e -> getCategoryNode(categoryMap, collectByParentId, e.getId())).toList();
        node.setChildren(category);
        return node;
    }
}
