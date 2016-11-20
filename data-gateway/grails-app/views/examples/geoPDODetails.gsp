<!DOCTYPE html>
<%@page import="liquibase.statement.core.CommentStatement"%>
<%@ defaultCodec="none" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
			
			.detailsTable {
				font-size: 12px;
				border-bottom: 1px solid #ddd;
			}
			.detailsTable .label {
				font-weight: bold;
				min-width: 100px;
			}
			.detailsTable .value {
				text-align: left;
			}
			.detailsTable tr td {
				text-align: left !important;
			}
			.commentText {
				display: block;
				width: 600px;
				margin-bottom: 5px;
			}
			.commentButton {
				position: absolute;
				right: 40px;
			}
			.comment {
				max-width: 600px;
				font-size: 12px;
				padding: 5px;
				border-bottom:  1px solid black;
			}
			.commentInfo {
				font-weight: bold;
				margin-bottom: 2px;
			}
			.commentDate {
				font-style: italic;
			}
			
			
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Navigation</h1>
			<ul>
				<li><g:link controller="examples" action="geoPDO">Back</g:link></li>
				<li><a href="http://pdonline.sunshinecoast.qld.gov.au/MasterView/Modules/Applicationmaster/default.aspx?page=wrapper&key=${ project.ram_process_ctr }" target="_blank">View in PD Online</a></li>
			</ul>
		</div>
		<div id="page-body" role="main">
			<h1>GeoPDO - ${ project.ram_id }</h1>
			<table class="detailsTable">
				<tr>
					<td class="label">
						Details
					</td>
					<td class="value">
						${ project.DESCRIPTION }
					</td>
				</tr>
				<tr>
					<td class="label">
						Category
					</td>
					<td class="value">
						${ project.group_desc }
					</td>
				</tr>
				<tr>
					<td class="label">
						Decision
					</td>
					<td class="value">
						${ project.DECISION }
					</td>
				</tr>
				<tr>
					<td class="label">
						Status
					</td>
					<td class="value">
						${ project.Progress }
					</td>
				</tr>
				<g:if test="${ project.D_Date_Rec }">
					<tr>
						<td class="label">
							Record Date
						</td>
						<td class="value">
							${ new Date(project.D_Date_Rec).format("dd MMM yyyy") }
						</td>
					</tr>
				</g:if>
				<tr>
					<td class="label">
						Land Number
					</td>
					<td class="value">
						${ project.land_no }
					</td>
				</tr>
				<tr>
					<td class="label">
						More Details
					</td>
					<td class="value">
						<a href="http://pdonline.sunshinecoast.qld.gov.au/MasterView/Modules/Applicationmaster/default.aspx?page=wrapper&key=${ project.ram_process_ctr }" target="_blank">View in PD Online</a>
					</td>
				</tr>
			</table>
			<g:if test="${ comments && comments.size() > 0 }">
				<h1>Comments</h1>
				<g:each var="comment" in="${ comments }">
					<div class="comment">
						<div class="commentInfo">
							<span class="user">${ comment.ip }</span> - <span class="commentDate">${ comment.timestamp.format("dd MMM yyyy @ hh:mm a") }</span>
						</div>
						<div class="commentText">
							${ comment.commentText.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br />") }
						</div>
					</div>
				</g:each>
			</g:if>
			
			<h1>Add Comment</h1>
			<form action="${createLink(uri: '/examples/geoPDOAddComment')}" style="position: relative; padding-bottom: 40px;">
				<textarea name="commentText" class="commentText" placeholder="type your comment here"></textarea>
				<input type="submit" class="commentButton" value="Submit" />
				<input type="hidden" name="project" class="projectData" value="" />
			</form>
			<script>
				$(document).ready(function(){
					var projectDetails = ${ project.toJSONString() }
					$(".projectData").val(JSON.stringify(projectDetails));
				});
			</script>
		</div>
	</body>
</html>
