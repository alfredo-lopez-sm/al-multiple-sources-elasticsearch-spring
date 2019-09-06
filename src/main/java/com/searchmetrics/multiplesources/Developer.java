package com.searchmetrics.multiplesources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "developer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Developer {
    @Id
    private String id;
    private String name;
}
