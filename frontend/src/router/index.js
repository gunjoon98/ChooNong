import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import CalculatorView from "../views/CalculatorView.vue";
import CheckListView from "../views/CheckListView.vue";
import RegionView from "../views/RegionView.vue";
import SurveyView from "../views/SurveyView.vue";
import MainComponent from "../components/main/MainComponent.vue";
import CalculatorComponent from "@/components/calculator/CalculatorComponent.vue";
import CalculatorResultComponent from "../components/calculator/CalculatorResultComponent.vue";
import CheckListComponent from "@/components/checklist/CheckListComponent.vue";
import CheckListResultComponent from "../components/checklist/CheckListResultComponent.vue";
import SurveyComponent from "@/components/survey/SurveyComponent.vue";
import SurveyResultComponent from "@/components/survey/SurveyResultComponent.vue";
import MainComponentC from "@/components/main/MainComponentC.vue";
import MainComponentCC from "@/components/main/MainComponentCC.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
      children: [
        {
          path: "/",
          name: "main",
          component: MainComponent,
        },
      ],
    },
    {
      path: "/survey",
      name: "survey",
      component: SurveyView,
      children: [
        {
          path: "/survey",
          name: "survey",
          component: SurveyComponent,
        },
        {
          path: "/survey/result",
          name: "surveyResult",
          props: true,
          component: SurveyResultComponent,
        },
      ]
    },
    // {
    //   path: "/region/:regionId",
    //   name: "region",
    //   props: true,
    //   component: RegionView,
    // },
    {
      path: "/region",
      name: "region",
      component: RegionView,
    },
    {
      path: "/calculator",
      name: "calculator",
      component: CalculatorView,
      children: [
        {
          path: "/calculator",
          name: "calculator",
          props: true,
          component: CalculatorComponent,
        },
        {
          path: "/calculator/result",
          name: "calculatorResult",
          props: true,
          component: CalculatorResultComponent,
        },
      ]
    },
    {
      path: "/checklist",
      name: "checklist",
      component: CheckListView,
      children: [
        {
          path: "/checklist",
          name: "checklist",
          component: CheckListComponent,
        },
        {
          path: "/checklist/result",
          name: "checklistResult",
          component: CheckListResultComponent,
        },
      ]
    },
  ],
});

export default router;
