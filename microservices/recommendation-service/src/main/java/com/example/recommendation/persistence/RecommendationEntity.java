package com.example.recommendation.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_recommendations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer version;
  private Integer productId;
  private String author;
  private int rating;
  private String content;
}
