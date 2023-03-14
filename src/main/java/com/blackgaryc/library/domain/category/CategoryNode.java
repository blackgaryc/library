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

    public CategoryNode(Category category,List<CategoryNode> children) {
        this(category);
        this.children = children;
    }

    public void setChildren(List<CategoryNode> children) {
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

    public static CategoryNode list2Node(List<Category> categories){
        Map<Integer, Category> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, o -> o));
        Map<Integer, List<Category>> collectByParentId = categories.stream().collect(Collectors.groupingBy(Category::getParentId));
        Integer integer = collectByParentId.keySet().stream().min(Comparator.comparingInt(value -> value)).orElse(0);
        return getCategoryNode(categoryMap, collectByParentId,integer);//从root节点递归出整个tree
    }

    @NotNull
    private static CategoryNode getCategoryNode(Map<Integer, Category> categoryMap, Map<Integer, List<Category>> collectByParentId,Integer integer) {
        //创建当前节点
        CategoryNode node = new CategoryNode(categoryMap.get(integer));
        //获取当前节点的孩子
        List<Category> categories = collectByParentId.get(integer);
        List<CategoryNode> category = Objects.nonNull(categories)?
                categories.stream().map(e-> getCategoryNode(categoryMap,collectByParentId,e.getId())).toList():Collections.emptyList();
                node.setChildren(category);
        return node;
    }
}
