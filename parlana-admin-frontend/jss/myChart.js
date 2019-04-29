
var chart = new Chart(document.getElementById("radar-chart"), {
    type: 'radar',
    data: {
      labels: ["Sadness (tristeza)", "Joy (Alegria)", "Fear (miedo)", "Disgust (disgusto)", "Anger (Enfado)"],
      datasets: [{
          label: "Emotion",
          fill: true,
          backgroundColor: "rgba(255,99,132,0.2)",
          borderColor: "rgba(255,99,132,1)",
          pointBorderColor: "#fff",
          pointBackgroundColor: "rgba(255,99,132,1)",
          pointBorderColor: "#fff",
          data: [0.0456,0.200,0.9,0.002,0.4]
        }
      ]
    },
    options: {
      title: {
        display: false,
        text: 'Distribution in % of world population'
      }
    }
});
