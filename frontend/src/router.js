import Vue from 'vue'
import Router from 'vue-router'
import Main from './views/Main.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/main',
      component: Main,
      children: [
        {
          path: '/',
          redirect: { name: 'student' }
        },
        {
          name: 'student',
          path: 'student',
          component: () => import('./views/Student.vue')
        },
        {
          path: 'student_add',
          component: () => import('./views/StudentAdd.vue')
        },
        {
          name: 'studentUpdate',
          path: 'student_update/:id',
          component: () => import('./views/StudentUpdate.vue')
        },
        {
          name: 'studentGradeAdd',
          path: 'student_grade_add/:id',
          component: () => import('./views/StudentGradeAdd.vue')
        },
        {
          path: 'course',
          component: () => import('./views/Course.vue')
        },
        {
          path: 'course_add',
          component: () => import('./views/CourseAdd.vue')
        },
        {
          name: 'courseUpdate',
          path: 'course_update/:code',
          component: () => import('./views/CourseUpdate.vue')
        },
        {
          path: 'grade',
          component: () => import('./views/Grade.vue')
        },
        {
          path: 'statistics',
          component: () => import('./views/Statistics.vue')
        },
      ]
    }
  ]
})
