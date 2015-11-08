package models

/**
  * Created by davidraleigh on 11/8/15.
  */

case class Task(id: Int, name: String)

object Task {
  private var m_taskList: List[Task] = List()

  def all: List[Task] = {
    m_taskList
  }

  def add(taskName: String) = {
    if (m_taskList.length == 0) {
      m_taskList = m_taskList ++ List(Task(1, taskName))
    } else {
      m_taskList = m_taskList ++ List(Task(m_taskList.last.id + 1, taskName))
    }


  }

  def delete(taskId: Int) = {
    m_taskList = m_taskList.filterNot(task => taskId == task.id)
  }
}
