## Quick Start

Requires Postgres and RabbitMQ to start:

	docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
	docker run --name rabbit -p 5671-5672:5671-5672 -p 15671-15672:15671-15672
	
	./gradlew bootRun
	
## Declared Queues

Report.Events(topic)        - ACTION.PRODUCED.HQ                   -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RECEIVED.GSUITE               -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RECEIVED.LWS                  -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RECEIVED.SERVICE_NOW          -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RECEIVED.XMA                  -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RESPONSE.PRODUCER.GSUITE      -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RESPONSE.PRODUCER.SERVICE_NOW -> FFA.Events.Exchange
Report.Events(topic)        - ACTION.RESPONSE.PRODUCER.XMA         -> FFA.Events.Exchange
Report.Events(topic)        - EXTRACT.LOGISTICS                    -> FFA.Events.Exchange
Report.Events(topic)        - EXTRACT.NISRA                        -> FFA.Events.Exchange
Report.Events(topic)        - EXTRACT.RCA                          -> FFA.Events.Exchange
Report.Events(topic)        - FSDR.SCHEDULAR                       -> FFA.Events.Exchange
Report.Events(topic)        - INGEST.ADECCO                        -> FFA.Events.Exchange
Report.Events(topic)        - INGEST.NISRA                         -> FFA.Events.Exchange
Report.Events(topic)        - MOCK.FSDR                            -> FFA.Events.Exchange
Report.Events(topic)        - PROCESSOR.ADECCO                     -> FFA.Events.Exchange
Report.Events(topic)        - PROCESSOR.EMPLOYEE                   -> FFA.Events.Exchange
Report.Events(topic)        - SERVICE.FSDR                         -> FFA.Events.Exchange
Report.Events(topic)        - SERVICE.GSUITE                       -> FFA.Events.Exchange

