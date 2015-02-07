<?xml version="1.0" encoding="UTF-8"?>
<root>
    <menu th:each="o:${main.btns}">
        <groupid th:text="${o.groupid}"></groupid>
        <name th:text="${o.name}"></name>
        <url th:text="${o.url}"></url>
    </menu>
</root>