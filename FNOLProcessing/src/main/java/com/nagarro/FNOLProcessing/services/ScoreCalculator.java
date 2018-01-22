package com.nagarro.FNOLProcessing.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.nagarro.FNOLProcessing.data.Constants;
import com.nagarro.FNOLProcessing.data.Pair;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class ScoreCalculator {
	public int sentimentsScore(final String text) {

		Properties props = new Properties();
		int sentiment = 5;
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Annotation annotation = pipeline.process(text);

		for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
			Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
			sentiment = RNNCoreAnnotations.getPredictedClass(tree);
			System.out.println(sentiment);
		}
		return sentiment;
	}

	public double getTotalScore(long ssn) {
		List<Pair<Long, Double>> creditStats = getList(Constants.CREDIT_CARD_STATS_PATH);
		List<Pair<Long, Double>> q1Score = getList(Constants.Q1_SCORE_CARD_STATS_PATH);
		List<Pair<Long, Double>> q2Score = getList(Constants.Q2_SCORE_CARD_STATS_PATH);

		double creditScore = 350;
		for (Pair<Long, Double> s : q2Score) {
			if (s.getKey() == ssn) {
				creditScore = s.getValue();
			}
		}

		double s1 = 0;
		for (Pair<Long, Double> pair : creditStats) {
			if (pair.getKey() > creditScore) {
				s1 = pair.getValue();
			}
		}

		double oldScore = 350;
		for (Pair<Long, Double> s : q1Score) {
			if (s.getKey() == ssn) {
				oldScore = s.getValue();
			}
		}

		double totalScore = Math.max(10, 20 + ((oldScore - creditScore) / 20) + s1 * 10);
		return totalScore;
	}

	private List<Pair<Long, Double>> getList(String path) {
		List<Pair<Long, Double>> l = new ArrayList<>();

		try {
			BufferedReader scan = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

			String s = null;
			while ((s = scan.readLine()) != null) {
				l.add(new Pair<Long, Double>(Long.parseLong(s.split(",")[0]), Double.parseDouble(s.split(",")[1])));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
}
