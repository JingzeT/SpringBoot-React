import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Dashboard from './components/Dashboard'
import Header from './components/Layout/Header';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AddProject from './components/Project/AddProject';
import { Provider } from 'react-redux';
import store from './store';
import UpdateProject from './components/Project/UpdateProject';
import ProjectBoard from './components/projectBoard/ProjectBoard';
import AddProjectTask from './components/projectBoard/ProjectTasks/AddProjectTask';
import UpdateProjectTask from './components/projectBoard/ProjectTasks/UpdateProjectTask';

const App = () => {

  return (
     <Provider store={store}>
       <Router>
         <div className='App'>
           <Header />
           <Routes>
              <Route exact path='/dashboard' element={<Dashboard />} />
              <Route exact path='/addProject' element={<AddProject />} />
              <Route exact path='/updateProject/:id' element={<UpdateProject />} />
              <Route exact path='/projectBoard/:id' element={<ProjectBoard />} />
              <Route exact path='/addProjectTasks/:id' element={<AddProjectTask />} />
              <Route exact path='/updateProjectTask/:backlog_id/:pt_id' element={<UpdateProjectTask />} />
           </Routes>
        </div>
      </Router>
    </Provider>
  );
};

export default App;