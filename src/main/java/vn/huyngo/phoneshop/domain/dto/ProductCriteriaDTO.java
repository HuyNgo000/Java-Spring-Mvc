package vn.huyngo.phoneshop.domain.dto;

import java.util.List;
import java.util.Optional;

public class ProductCriteriaDTO {
    private Optional<String> page;
    private Optional<List<String>> factory;
    private Optional<List<String>> screen;
    private Optional<List<String>> rom;
    private Optional<List<String>> price;
    private Optional<List<String>> name;
    private Optional<String> sort;

    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<List<String>> getFactory() {
        return factory;
    }

    public void setFactory(Optional<List<String>> factory) {
        this.factory = factory;
    }

    public Optional<List<String>> getScreen() {
        return screen;
    }

    public void setScreen(Optional<List<String>> screen) {
        this.screen = screen;
    }

    public Optional<List<String>> getRom() {
        return rom;
    }

    public void setRom(Optional<List<String>> rom) {
        this.rom = rom;
    }

    public Optional<List<String>> getPrice() {
        return price;
    }

    public void setPrice(Optional<List<String>> price) {
        this.price = price;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

    public Optional<List<String>> getName() {
        return name;
    }

    public void setName(Optional<List<String>> name) {
        this.name = name;
    }

}
